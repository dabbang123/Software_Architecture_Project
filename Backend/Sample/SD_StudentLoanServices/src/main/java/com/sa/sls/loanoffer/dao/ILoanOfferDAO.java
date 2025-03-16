package com.sa.sls.loanoffer.dao;

import java.util.List;
import java.util.Map;

import com.sa.sls.loanoffer.model.LoanOffer;

public interface ILoanOfferDAO {
    public int generateOffer (LoanOffer loanOffer);

    public LoanOffer getLoanOffer(int offerID);

    public boolean checkIfOfferExists(Map<String, Object> userValues);

    public List<LoanOffer> getAllOffers();

    public int disburseLoanOffer(int applicationID);

    public int generateLoanOfferStatus (int applicationID);
    
    public List<LoanOffer> checkGeneratedOffers (Long applicationId);
    
    public int updateOfferStatusFromApplicant (Map<String, Object> userValues);
    
    public int getLoanApplicationId (int offerId);
}
