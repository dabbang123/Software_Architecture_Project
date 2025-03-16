package com.sa.sls.studentservice.applicant.bl;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;

import com.sa.sls.studentservice.applicant.model.Applicant;

public interface IApplicantBL 
{
	public Map<String, Boolean> registerApplicantDraft(Map<String, Object> userValues);
	
	public Map<String, Boolean> registerApplicant(Long userId);
	
	public Applicant getApplicantDetailsByName (String firstName, String lastName);
}
