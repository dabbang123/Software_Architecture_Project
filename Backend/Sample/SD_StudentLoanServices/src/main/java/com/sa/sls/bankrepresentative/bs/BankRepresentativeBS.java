package com.sa.sls.bankrepresentative.bs;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.sls.bankrepresentative.bl.IBankRepresentativeBL;
import com.sa.sls.bankrepresentative.model.BankRepresentative;

/*
 * Author: Rushabh Botadra
 */

@Service
public class BankRepresentativeBS implements IBankRepresentativeBS{

	@Autowired
	private IBankRepresentativeBL bankRepresentativeBL;
	
	@Override
	public List<BankRepresentative> getAllBankRepresentatives()
	{
		return bankRepresentativeBL.getAllBankRepresentatives();
	}
	
	@Override
	public Map<String, Boolean> reviewGuarantor(int applicationId)
	{
		return bankRepresentativeBL.reviewGuarantor(applicationId);
	}
}
