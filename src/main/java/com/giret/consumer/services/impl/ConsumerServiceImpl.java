package com.giret.consumer.services.impl;

import com.giret.consumer.repository.LoanRepository;
import com.giret.consumer.repository.ResourceRepository;
import com.giret.consumer.services.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    ResourceRepository resourceRepository;


    @Override
    public void updateState(Long resourceId, String state) {
        resourceRepository.updateState(resourceId, state);
    }
}
