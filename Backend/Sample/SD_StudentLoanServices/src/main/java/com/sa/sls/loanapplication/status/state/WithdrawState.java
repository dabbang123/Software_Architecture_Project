package com.sa.sls.loanapplication.status.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sa.sls.loanapplication.dao.ILoanApplicationDAO;

/*
 * @Author: Abhishek Vishwakarma
 */
@Component
public class WithdrawState implements IApplicationStatusState 
{
	@Autowired
	private ILoanApplicationDAO loanApplicationDAO;

	@Override
	public int updateStatus(int loanApplicationId) {
		return loanApplicationDAO.withdrawApplication(loanApplicationId);
	}
}
