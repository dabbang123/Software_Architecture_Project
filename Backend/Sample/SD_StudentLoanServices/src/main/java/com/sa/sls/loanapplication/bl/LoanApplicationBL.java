package com.sa.sls.loanapplication.bl;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.sls.applicant.bl.IApplicantBL;
import com.sa.sls.applicant.constants.ApplicantConstants;
import com.sa.sls.applicant.dao.IApplicantDAO;
import com.sa.sls.applicant.model.Applicant;
import com.sa.sls.bankadmin.dao.IBankAdminDAO;
import com.sa.sls.bankadmin.model.BankAdmin;
import com.sa.sls.bankrepresentative.constant.BankRepresentativeConstants;
import com.sa.sls.guarantor.dao.IGuarantorDAO;
import com.sa.sls.guarantor.model.Guarantor;
import com.sa.sls.loanapplication.constants.LoanApplicationConstants;
import com.sa.sls.loanapplication.dao.ILoanApplicationDAO;
import com.sa.sls.loanapplication.model.LoanApplication;
import com.sa.sls.loanapplication.status.ApplicationStatus;
import com.sa.sls.loanapplication.status.context.ApplicationStatusContext;

@Service
public class LoanApplicationBL implements ILoanApplicationBL 
{
	@Autowired
	private ILoanApplicationDAO loanApplicationDAO;
	
	@Autowired
	private IApplicantBL applicantBL;
	
	@Autowired
	private IApplicantDAO applicantDAO;
	
	@Autowired
	private IBankAdminDAO bankAdminDAO;
	
	@Autowired
	private IGuarantorDAO guarantorDAO;
	
	@Autowired
	private ApplicationStatusContext applicationStatusContext;
	
	@Override
	public Map<String, Object> submitApplication (Map<String, Object> userValues)
	{
		Map<String, Object> returnMap = new HashMap<>();
		LoanApplication loanApplication = createLoanApplication(userValues);
		if (loanApplication.getApplicant() == null)
		{
			returnMap.put(ApplicantConstants.APPLICANT_DOES_NOT_EXISTS, true);
			return returnMap;
		}
		if (checkIfLoanExistWithApplicant(loanApplication))
		{
			returnMap.put(loanApplication.getApplicant().getFirstName() + LoanApplicationConstants.EXISTING_LOAN_EXISTS, true);
			return returnMap;
		}
		
		if (loanApplicationDAO.submitApplication(loanApplication) == 1)
		{
			Applicant applicant = loanApplication.getApplicant();
			loanApplication = loanApplicationDAO.getLoanApplication(loanApplication.getApplicant().getApplicantId(), loanApplication.getLoanAmount());
			loanApplication.setApplicant(applicant);
			returnMap.put(LoanApplicationConstants.LOAN_SUBMITTED_SUCCESSFULLY, loanApplication);
		}
		
		return returnMap;
	}
	
	@Override
	public Long getApplicationId (String email)
	{
		LoanApplication application = loanApplicationDAO.getApplicationId(email);
		return application == null ? null : Long.valueOf(application.getApplicationId());
	}

	@Override
	public String updateApplication(Map<String, Object> userValues) 
	{
		LoanApplication application = createLoanApplication(userValues);
		application.setApplicationId(Integer.valueOf(Objects.toString(userValues.get("applicationId"))));
		if (loanApplicationDAO.updateApplication(application) == 1)
		{
			return LoanApplicationConstants.APPLICATION_UPDATED_SUCCESSFULLY;
		}
		
		return LoanApplicationConstants.APPLICATION_UPDATION_FAILED;
	}
	
	@Override
	public String withdrawApplication (Map<String, Object> userValues)
	{
		//Using State Design Pattern to change the state and set the status of application to Withdraw State
		applicationStatusContext.setState(userValues);
		return applicationStatusContext.updateLoanApplicationStatus(Integer.valueOf(Objects.toString(userValues.get(LoanApplicationConstants.APPLICATION_ID)))) == 1 ? LoanApplicationConstants.LOAN_APPLICATION_WITHDRAWN : LoanApplicationConstants.LOAN_APPLICATION_WITHDRAW_FAILED;
	}
	
	public LoanApplication createLoanApplication (Map<String, Object> userValues)
	{
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setApplicant(applicantBL.getApplicantDetailsByName(Objects.toString(userValues.get(LoanApplicationConstants.FIRST_NAME)), Objects.toString(userValues.get(LoanApplicationConstants.LAST_NAME))));
		loanApplication.setGuarantor(new Guarantor());
		loanApplication.getGuarantor().setName(Objects.toString(userValues.get(LoanApplicationConstants.GUARANTOR)));
		loanApplication.setApplicationDate(new Date(new java.util.Date().getTime()));
		loanApplication.setLoanAmount(Long.valueOf(Objects.toString(userValues.get(LoanApplicationConstants.LOAN_AMOUNT))));
		loanApplication.setPurpose(Objects.toString(userValues.get(LoanApplicationConstants.PURPOSE)));
		loanApplication.setStatus(Objects.toString(ApplicationStatus.DRAFT.getStatus()));
		loanApplication.setAssigneId(null);
		return loanApplication;
	}
	
	private boolean checkIfLoanExistWithApplicant(LoanApplication loanApplication)
	{
		return loanApplicationDAO.checkIfLoanExistWithApplicant(loanApplication);
	}

	@Override
	public List<LoanApplication> getApprovedApplications () {
		List<LoanApplication> loanApplicationList = loanApplicationDAO.getApprovedApplications();
		for (LoanApplication loanApplication : loanApplicationList) {
			loanApplication.setApplicant(applicantDAO.getApplicantDetailsByLoanApplication(loanApplication.getApplicationId()));
			loanApplication.setGuarantor(guarantorDAO.getGuarantorByAppId(loanApplication.getApplicationId()));
			loanApplication.setAssigneId((BankAdmin) bankAdminDAO.getBankAdminForLoanApplication(loanApplication.getApplicationId()));
		}
		return loanApplicationList;
	}
	
	@Override
	public Map<String, Boolean> assignApplication(Map<String, Object> userValues)  
	{
		Map<String, Boolean>returnMap = new HashMap<>();
		if(loanApplicationDAO.assignApplication(userValues) == 1)
		{
			//Used State Pattern to Update the State to Approved
			applicationStatusContext.setState(userValues);
			if(applicationStatusContext.updateLoanApplicationStatus(Integer.valueOf(Objects.toString(userValues.get(LoanApplicationConstants.APPLICATION_ID)))) == 1 );
			{
				returnMap.put(BankRepresentativeConstants.APPLICATION_UPDATED_SUCCESSFULLY, true);
			}
		}
		else
		{
			returnMap.put(BankRepresentativeConstants.UPDATION_FAILED, false);
		}
		return returnMap;
	}	
	
// Get all the applications
	@Override
	public List<LoanApplication> getAllLoanApplications() {
		List<LoanApplication> loanApplications = loanApplicationDAO.getAllLoanApplications();
		for (LoanApplication loanApplication : loanApplications) 
		{
			loanApplication.setApplicant(applicantDAO.getApplicantDetailsByLoanApplication(loanApplication.getApplicationId()));
			loanApplication.setGuarantor(guarantorDAO.getGuarantorByAppId(loanApplication.getApplicationId()));
		}
		return loanApplications;
	}

// Approve the application
	@Override
	public String approveApplication (Map<String, Object> userValues)
	{
		//Used State Design Pattern to set the application status to Approved
		applicationStatusContext.setState(userValues);
		return applicationStatusContext.updateLoanApplicationStatus(Integer.parseInt(Objects.toString(userValues.get(LoanApplicationConstants.APPLICATION_ID)))) == 1 ? LoanApplicationConstants.LOAN_APPLICATION_APPROVED : LoanApplicationConstants.LOAN_APPLICATION_APPROVED_FAILED;
		
	}	
	
// Reject the application - RB	
	@Override
	public String rejectApplication (Map<String, Object> userValues)
	{
		//Used State Design Pattern to set the application status to Rejected
		applicationStatusContext.setState(userValues);
		return applicationStatusContext.updateLoanApplicationStatus(Integer.parseInt(Objects.toString(userValues.get(LoanApplicationConstants.APPLICATION_ID)))) == 1 ? LoanApplicationConstants.LOAN_APPLICATION_REJECTED : LoanApplicationConstants.LOAN_APPLICATION_REJECTION_FAILED;
		
	}
}
