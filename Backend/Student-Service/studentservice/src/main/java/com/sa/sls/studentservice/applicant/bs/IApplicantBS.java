package com.sa.sls.studentservice.applicant.bs;

/*
 * @Author: Ranatosh Sarkar
 */

import java.util.Map;

public interface IApplicantBS 
{
	public Map<String, Boolean> registerApplicantDraft(Map<String, Object> userValues);
	
	public Map<String, Boolean> registerApplicant(Long userId);
}
