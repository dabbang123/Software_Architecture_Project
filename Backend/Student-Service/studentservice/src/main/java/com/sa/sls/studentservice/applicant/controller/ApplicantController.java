package com.sa.sls.studentservice.applicant.controller;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sa.sls.studentservice.applicant.bs.IApplicantBS;
import com.sa.sls.studentservice.applicant.constants.ApplicantConstants;
import com.sa.sls.studentservice.interceptor.dp.InterceptorFramework;
import com.sa.sls.studentservice.interceptor.dp.LoggingInterceptor;
import com.sa.sls.studentservice.user.bs.IUserBusinessService;


@RequestMapping("/applicant")
@RestController
public class ApplicantController {
	
	@Autowired
	private IApplicantBS applicantBS;
	
	@Autowired
	private IUserBusinessService userBusinessService;
	
//	@Autowired
//	private ILoanApplicationBS loanApplicationBS;
//	
//	@Autowired
//	private ILoanOfferBS loanOfferBS;
//	
//	@Autowired
//	private Subject subject;
	
	@Autowired
	private LoggingInterceptor interceptor;
	
	@Autowired
	private InterceptorFramework interceptorFramework;

//	@Autowired
//	public ApplicantController(Subject subject, AdminNotificationService adminNotificationService) {
//		this.subject = subject;
//		this.subject.addObserver(adminNotificationService);
//	}
	
	//Added by Ranatosh Sarkar
	@PostMapping("/register")
	public ResponseEntity<String> registerApplicant(@RequestBody Map<String, Object> userValues) 
	{
		Map<String, Boolean> resultMap = applicantBS.registerApplicantDraft(userValues);
		if (resultMap.containsKey(ApplicantConstants.APPLICANT_REGISTERED)) 
		{
			return resultMap.get(ApplicantConstants.APPLICANT_REGISTERED) == true
					? new ResponseEntity<>(ApplicantConstants.APPLICANT_REGISTERED_SUCCESSFULLY, HttpStatus.OK)
					: new ResponseEntity<>(ApplicantConstants.APPLICANT_REGISTERED_FAILED, HttpStatus.NOT_FOUND);
		}
		else if (resultMap.containsKey(ApplicantConstants.APPLICANT_ALREADY_REGISTERED)) 
		{
			return new ResponseEntity<>(ApplicantConstants.APPLICANT_ALREADY_REGISTERED, HttpStatus.OK);
		} 
		else if (resultMap.containsKey(ApplicantConstants.NO_USER_FOUND))
		{
			return new ResponseEntity<>(ApplicantConstants.NO_USER_FOUND, HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<>(ApplicantConstants.BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
	}
	
	//Added by Ranatosh Sarkar
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody Map<String, Object> userValues) {
		String userName = Objects.toString(userValues.get(ApplicantConstants.EMAIL));
		String password = Objects.toString(userValues.get(ApplicantConstants.PASSWORD));
		return userBusinessService.loginUser(userName, password) == true
				? new ResponseEntity<>(ApplicantConstants.APPLICANT_LOGGED_IN_SUCCESSFULLY, HttpStatus.OK)
				: new ResponseEntity<>(ApplicantConstants.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/submitApplication")
	public ResponseEntity<String> submitApplication(@RequestBody Map<String, Object> userValues)
	{
//		Map<String, Object> resultMap = loanApplicationBS.submitApplication(userValues);
//		String key = "";
//		for (Map.Entry<String, Object> entry : resultMap.entrySet()) 
//		{
//            key = entry.getKey();
//        }
//		
//		if (resultMap.containsKey(ApplicantConstants.APPLICANT_DOES_NOT_EXISTS))
//		{
//			return ResponseEntity.ok(ApplicantConstants.APPLICANT_DOES_NOT_EXISTS);
//		}
//		
//		if (key.equals(LoanApplicationConstants.LOAN_SUBMITTED_SUCCESSFULLY))
//		{
//			LoanApplication application = (LoanApplication) resultMap.get(LoanApplicationConstants.LOAN_SUBMITTED_SUCCESSFULLY);
//			
//			//Interceptor Design Pattern
//			interceptorFramework.registerInterceptor(interceptor);
//			Context context = new Context();
//			context.put("applicationDetails", application);
//			interceptorFramework.execute(context);
//			return ResponseEntity.ok(key + application.getApplicationId());
//		}
//		return ResponseEntity.ok(key);
		return null;
	}
	
	@GetMapping("/getApplicationId")
	public ResponseEntity<String> getApplicationId (@RequestParam String email)
	{
//		String applicationId = Objects.toString(loanApplicationBS.getApplicationId(email));
//		return ResponseEntity.ok(applicationId == "null" ? LoanApplicationConstants.NO_LOAN_APPLICATION_FOUND : LoanApplicationConstants.LOAN_APPLICATION_FOUND + applicationId);
		return null;
	}
	
	@PutMapping("/updateApplication/{applicationId}")
	public ResponseEntity<String> updateApplication (@PathVariable(ApplicantConstants.APPLICATION_ID) Long applicationId, @RequestBody Map<String, Object> userValues)
	{
//		userValues.put("applicationId", applicationId);
//		return ResponseEntity.ok(loanApplicationBS.updateApplication(userValues));
		return null;
	}
	
	@PutMapping("/withdrawApplication/{applicationId}")
	public ResponseEntity<String> withdrawApplication(@PathVariable(ApplicantConstants.APPLICATION_ID) Long applicationId)
	{
//		Map<String, Object> userValues = new HashMap<String, Object>();
//		userValues.put(ApplicantConstants.APPLICATION_ID, applicationId);
//		userValues.put(LoanApplicationConstants.ACTION, LoanApplicationConstants.WITHDRAW);
//		return ResponseEntity.ok(loanApplicationBS.withdrawApplication(userValues));
		return null;
	}
	
	@GetMapping("/checkGeneratedOffers/{applicationId}")
	public ResponseEntity<Object> checkGeneratedOffers(@PathVariable(ApplicantConstants.APPLICATION_ID) Long applicationId)
	{
//		List<LoanOffer> offerList = loanOfferBS.checkGeneratedOffers(applicationId);
//		return offerList != null ? new ResponseEntity<>(offerList.get(0), HttpStatus.OK) : new ResponseEntity<>("No Offers Generated", HttpStatus.NOT_FOUND);
		return null;
	}
	
	@PutMapping("/acceptOffer/{applicationId}")
	public ResponseEntity<String> acceptOffer(@PathVariable(ApplicantConstants.APPLICATION_ID) Long applicationId)
	{
//		Map<String, Object> userValues = new HashMap<String, Object>();
//		userValues.put(ApplicantConstants.APPLICATION_ID, applicationId);
//		userValues.put(LoanApplicationConstants.ACTION, "ACCEPTED");
//		return loanOfferBS.updateOfferStatusFromApplicant(userValues) == true ? new ResponseEntity<>("Loan Offer Accepted", HttpStatus.OK) : new ResponseEntity<>("Loan Offer Acceptence Failed", HttpStatus.BAD_REQUEST);
		return null;
	}
	
	@PutMapping("/rejectOffer/{applicationId}")
	public ResponseEntity<String> rejectOffer(@PathVariable(ApplicantConstants.APPLICATION_ID) Long applicationId)
	{
//		Map<String, Object> userValues = new HashMap<String, Object>();
//		userValues.put(ApplicantConstants.APPLICATION_ID, applicationId);
//		userValues.put(LoanApplicationConstants.ACTION, "REJECTED");
//		return loanOfferBS.updateOfferStatusFromApplicant(userValues) == true ? new ResponseEntity<>("Loan Offer Rejected", HttpStatus.OK) : new ResponseEntity<>("Loan Offer Rejection Failed", HttpStatus.BAD_REQUEST);
		return null;
	}
	
	@PostMapping("/user/register")
	public void registeruser(@RequestBody Map<String, Object> userValues)
	{
		userBusinessService.registerUser(userValues);
	}
}
