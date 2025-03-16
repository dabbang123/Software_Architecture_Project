package com.sa.sls.baservice.bl;


import java.util.List;
import java.util.Map;

import com.sa.sls.baservice.model.BankAdmin;

public interface IBankAdminBL {
    public Double calculateSanctionAmount (int applicationId);

    public List<BankAdmin> getBankAdmins ();

    public String disburseLoanOffer(Map<String, Object> userValues);
}
