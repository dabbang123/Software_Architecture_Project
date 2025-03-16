package com.sa.sls.bankrepresentative.bl;

/*
 * Author: Rushabh Botadra
 */

import java.util.Map;
import java.util.List;

import com.sa.sls.bankrepresentative.model.BankRepresentative;

public interface IBankRepresentativeBL {
	
	public List<BankRepresentative> getAllBankRepresentatives();
	
	public Map<String, Boolean> reviewGuarantor(int applicationId);
}
