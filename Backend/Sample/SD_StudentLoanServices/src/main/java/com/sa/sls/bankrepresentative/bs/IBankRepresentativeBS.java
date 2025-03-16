package com.sa.sls.bankrepresentative.bs;

/*
 * Author: Rushabh Botadra
 */
import java.util.Map;
import java.util.List;

import com.sa.sls.bankrepresentative.model.BankRepresentative;

public interface IBankRepresentativeBS {
	
	public List<BankRepresentative> getAllBankRepresentatives();
	
	public Map<String,Boolean> reviewGuarantor(int applicationId);
}
