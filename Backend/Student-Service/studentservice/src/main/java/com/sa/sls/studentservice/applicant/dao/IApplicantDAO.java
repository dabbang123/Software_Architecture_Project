package com.sa.sls.studentservice.applicant.dao;

import com.sa.sls.studentservice.applicant.model.Applicant;

/*
 * @Author: Abhishek Vishwakarma
 */


public interface IApplicantDAO 
{
	public int registerApplicant(Applicant applicant);
	
	public int registerApplicantDraft(Applicant applicant);
	
	public boolean checkIfApplicantAlreadyExists(Applicant applicant);
	
	public Applicant getApplicantDetailsByName (String firstName, String lastName);
	
	public Applicant getApplicantDetailsByNameFromDraft (String firstName, String lastName);
	
	public Applicant getApplicantDetailsByUserIdFromDraft (Long userId);
	
	public int deleteApplicantFromDraft(int userId);
	
// Get Applicant By Application Id
	public Applicant getApplicantDetailsByApplId(int applicationId);
	
	public Applicant getApplicantDetailsByLoanApplication(int loanApplicationId);
	
		public boolean checkIfApplicantExists(int applicantId);
}
