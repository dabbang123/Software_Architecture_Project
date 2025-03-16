package com.sa.sls.loanapplication.status.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sa.sls.loanapplication.dao.ILoanApplicationDAO;

/*
 * @Author: Abhishek Vishwakarma
 * @Author: Rushabh Botadra
 */
@Component
public class UnderReviewState implements IApplicationStatusState{


	@Autowired
	private ILoanApplicationDAO loanApplicationDAO;
	
	@Override
	public int updateStatus(int loanApplicationId) {
		return loanApplicationDAO.underReviewApplication(loanApplicationId);
	}
}
