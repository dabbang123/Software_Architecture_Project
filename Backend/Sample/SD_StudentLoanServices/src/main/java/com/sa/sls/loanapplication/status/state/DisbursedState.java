package com.sa.sls.loanapplication.status.state;

import com.sa.sls.loanapplication.dao.LoanApplicationDAO;
import org.springframework.stereotype.Component;

/*
 * @Author: Abhishek Vishwakarma
 */
@Component
public class DisbursedState implements IApplicationStatusState {

	private final LoanApplicationDAO loanApplicationDAO;

	public DisbursedState(LoanApplicationDAO loanApplicationDAO) {
		this.loanApplicationDAO = loanApplicationDAO;
	}

	@Override
	public int updateStatus(int applicationId) {
		return loanApplicationDAO.disburseLoanApplication(applicationId);
	}

}
