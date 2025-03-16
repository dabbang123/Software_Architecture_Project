package com.sa.sls.studentservice.user.bs;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;

import com.sa.sls.studentservice.user.model.User;


public interface IUserBusinessService {
	public Map<String, Boolean> registerUser (Map<String, Object> userValues);

	public boolean loginUser(String email, String password);
	
	public Map<String, Boolean> updateprofile(Map<String, Object> userValues);
	
	public User findUserByEmail(String email);
}
