package com.giret.consumer.services.impl;

import com.giret.consumer.client.LoanClient;
import com.giret.consumer.client.ResourceClient;
import com.giret.consumer.model.Loan;
import com.giret.consumer.model.Resource;
import com.giret.consumer.services.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService{

    private final ResourceClient resourceClient;
    private final LoanClient loanClient;


    @Override
    public Resource updateStateResource(Long resourceId, String state) {
          return resourceClient.updateResourceByState(resourceId,state);
    }

    @Override
    public Loan updateLoanByState(String state,Long id) {
        return loanClient.updateLoanByState(state,id);
    }
}
