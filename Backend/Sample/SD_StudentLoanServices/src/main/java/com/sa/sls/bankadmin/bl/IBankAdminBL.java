package com.sa.sls.bankadmin.bl;

/*
 * @Author: Nikunj Panchal
 */

import com.sa.sls.bankadmin.model.BankAdmin;

import java.util.List;
import java.util.Map;

public interface IBankAdminBL {
    public Double calculateSanctionAmount (int applicationId);

    public List<BankAdmin> getBankAdmins ();

    public String disburseLoanOffer(Map<String, Object> userValues);
}
