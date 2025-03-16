package com.sa.sls.command.dp;

/*
 * @Author: Nikunj Panchal
 */

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sa.sls.bankrepresentative.constant.BankRepresentativeConstants;
import com.sa.sls.externaldb.dao.ExternalDbDAO;
import com.sa.sls.guarantor.model.Guarantor;

@Component
public class BusinessGuarantorReviewCommand implements IGuarantorReviewCommand 
{
	@Autowired
	private ExternalDbDAO externalDbDAO;
	
	@Override
	public Map<String, Boolean> execute(Guarantor guarantor) 
	{
		Map<String, Boolean> resultMap = new HashMap<>();
        if (externalDbDAO.checkItrForBusiness(guarantor.getUinNo())) 
        {
            resultMap.put(BankRepresentativeConstants.GUARANTOR_REVIEWED_SUCCESSFULLY, true);
        }
        else 
        {
            resultMap.put(BankRepresentativeConstants.GUARANTOR_REVIEWED_FAILED, false);
        }
        return resultMap;
	}
}
