package com.sa.sls.command.dp;
/*
 * @Author: Nikunj Panchal
 */

import java.util.Map;

import com.sa.sls.guarantor.model.Guarantor;


public interface IGuarantorReviewCommand {
	public Map<String, Boolean> execute(Guarantor guarantor);
}
