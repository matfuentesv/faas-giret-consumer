package com.giret.consumer.services;

import com.giret.consumer.model.Loan;
import com.giret.consumer.model.Resource;

public interface ConsumerService {


    Resource updateStateResource (Long resourceId,String state);
    Loan updateLoanByState (String state,Long id);
}
