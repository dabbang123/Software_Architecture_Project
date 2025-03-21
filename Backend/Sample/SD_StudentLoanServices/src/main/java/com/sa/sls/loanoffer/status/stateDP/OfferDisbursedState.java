package com.sa.sls.loanoffer.status.stateDP;

/*
 * @Author: Nikunj Panchal
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sa.sls.loanoffer.dao.LoanOfferDAO;

@Component
public class OfferDisbursedState implements ILoanOfferStatusState {

    @Autowired
    private LoanOfferDAO loanOfferDAO;

    @Override
    public int updateStatus (int offerId) {
        return (loanOfferDAO.disburseLoanOffer(offerId));
    }
}
