package com.sa.sls.loanoffer.bs;

import java.util.List;
import java.util.Map;

import com.sa.sls.loanoffer.model.LoanOffer;

public interface ILoanOfferBS {
    public Map<String, Boolean> generateOffer(Map<String, Object> userValues);

    public List<LoanOffer> getAllOffers();
    
    public List<LoanOffer> checkGeneratedOffers (Long applicationId);
    
    public boolean updateOfferStatusFromApplicant (Map<String, Object> userValues);
}
