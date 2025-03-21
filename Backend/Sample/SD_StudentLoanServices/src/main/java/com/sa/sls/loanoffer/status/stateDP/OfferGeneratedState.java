package com.sa.sls.loanoffer.status.stateDP;

import com.sa.sls.bankadmin.dao.IBankAdminDAO;
import com.sa.sls.loanoffer.dao.ILoanOfferDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferGeneratedState implements ILoanOfferStatusState {

    @Autowired
    private ILoanOfferDAO loanOfferDAO;

    @Override
    public int updateStatus (int applicationId) {
        return loanOfferDAO.generateLoanOfferStatus(applicationId);
    }
}