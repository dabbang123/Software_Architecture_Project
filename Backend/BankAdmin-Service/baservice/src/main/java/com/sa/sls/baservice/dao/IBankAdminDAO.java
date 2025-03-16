package com.sa.sls.baservice.dao;

import java.util.List;

import com.sa.sls.baservice.model.BankAdmin;

public interface IBankAdminDAO {
    public List<BankAdmin> getBankAdmins ();

    public int disburseLoanOffer (int offerID);
    
    public BankAdmin getBankAdminForLoanApplication (int loanApplicationId);
}
