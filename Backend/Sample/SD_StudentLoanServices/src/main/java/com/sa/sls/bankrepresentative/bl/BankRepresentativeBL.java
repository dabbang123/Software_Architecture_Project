package com.sa.sls.bankrepresentative.bl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.sls.bankrepresentative.dao.IBankRepresentativeDAO;
import com.sa.sls.bankrepresentative.model.BankRepresentative;
import com.sa.sls.command.dp.IGuarantorReviewCommand;
import com.sa.sls.command.factory.dp.GuarantorReviewCommandFactory;
import com.sa.sls.guarantor.dao.IGuarantorDAO;
import com.sa.sls.guarantor.model.Guarantor;

/*
 * Author: Rushabh Botadra
 */

@Service
public class BankRepresentativeBL implements IBankRepresentativeBL 
{
	@Autowired
	private IBankRepresentativeDAO bankRepresentativeDAO;
	
	@Autowired
	private IGuarantorDAO guarantorDAO;
	
	@Autowired
	private GuarantorReviewCommandFactory reviewCommandFactory;
	
	@Override
	public List<BankRepresentative> getAllBankRepresentatives()
	{
		return bankRepresentativeDAO.getAllBankRepresentatives();
	}
	
	@Override
	public Map<String, Boolean> reviewGuarantor(int applicationId)
	{
		Guarantor guarantor = guarantorDAO.getGuarantorByAppId(applicationId); 
		//Using Command Design Pattern
	    IGuarantorReviewCommand command = reviewCommandFactory.getCommand(guarantor.getOccupation());
		return command.execute(guarantor);
	}
}
