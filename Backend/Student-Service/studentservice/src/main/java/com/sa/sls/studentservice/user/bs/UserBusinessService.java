package com.sa.sls.studentservice.user.bs;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.sls.studentservice.user.bl.IUserBusinessLogic;
import com.sa.sls.studentservice.user.model.User;


@Service
public class UserBusinessService implements IUserBusinessService {

	@Autowired
	private IUserBusinessLogic userBusinessLogic;

	public Map<String, Boolean> registerUser(Map<String, Object> userValues) 
	{
		return userBusinessLogic.registerUser(userValues);
	}

	@Override
	public boolean loginUser(String email, String password) 
	{
		return userBusinessLogic.loginUser(email, password);
	}

	@Override
	public Map<String, Boolean> updateprofile(Map<String, Object> userValues) 
	{
		return userBusinessLogic.updateprofile(userValues);
	}
	
	@Override
	public User findUserByEmail(String email)
	{
		return userBusinessLogic.findUserByEmail(email);
	}
}
