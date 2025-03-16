package com.sa.sls.loanoffer.factory;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* Author: Nikunj Panchal
*/
import com.sa.sls.loanoffer.status.stateDP.ILoanOfferStatusState;
import com.sa.sls.loanoffer.status.stateDP.OfferAcceptedState;
import com.sa.sls.loanoffer.status.stateDP.OfferDisbursedState;
import com.sa.sls.loanoffer.status.stateDP.OfferGeneratedState;
import com.sa.sls.loanoffer.status.stateDP.OfferRejectedState;

@Component
public class LoanOfferStatusFactory {
    @Autowired
    private OfferAcceptedState acceptedState;

    @Autowired
    private OfferRejectedState rejectedState;

    @Autowired
    private OfferDisbursedState disburseState;

    @Autowired
    private OfferGeneratedState generatedState;

    public ILoanOfferStatusState getLoanOfferState(Map<String, Object> userValues) {
        return switch (Objects.toString(userValues.get("action"))) {
            case "accepted" -> acceptedState;
            case "rejected" -> rejectedState;
            case "generated" -> generatedState;
            case "disburse" -> disburseState;
            default -> throw new IllegalArgumentException("Unexpected value: " + userValues.get("action"));
        };
    }
}