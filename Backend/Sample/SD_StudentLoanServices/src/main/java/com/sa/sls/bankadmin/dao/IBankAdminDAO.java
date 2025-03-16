package com.sa.sls.bankadmin.dao;

/*
 * @Author: Nikunj Panchal
 */

import com.sa.sls.bankadmin.model.BankAdmin;

import java.util.List;
import java.util.Map;

public interface IBankAdminDAO {
    public List<BankAdmin> getBankAdmins ();

    public int disburseLoanOffer (int offerID);
    
    public BankAdmin getBankAdminForLoanApplication (int loanApplicationId);
}
