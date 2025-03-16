package com.sa.sls.command.factory.dp;

/*
 * @Author: Nikunj Panchal
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sa.sls.command.dp.BusinessGuarantorReviewCommand;
import com.sa.sls.command.dp.IGuarantorReviewCommand;
import com.sa.sls.command.dp.SalariedGuarantorReviewCommand;
import com.sa.sls.guarantor.constants.GuarantorConstants;

@Component
public class GuarantorReviewCommandFactory 
{
	@Autowired
	private BusinessGuarantorReviewCommand businessGuarantorReviewCommand;
	
	@Autowired
	private SalariedGuarantorReviewCommand salariedGuarantorReviewCommand;
	
	public IGuarantorReviewCommand getCommand(String occupation) 
	{
        if (GuarantorConstants.BUSINESS.equals(occupation)) 
        {
            return businessGuarantorReviewCommand;
        } 
        else 
        {
            return salariedGuarantorReviewCommand;
        }
    }
}
