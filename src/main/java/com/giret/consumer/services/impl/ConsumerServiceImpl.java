package com.giret.consumer.services.impl;

import com.giret.consumer.client.ResourceClient;
import com.giret.consumer.model.Resource;
import com.giret.consumer.services.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService{

    private final ResourceClient resourceClient;


    @Override
    public List<Resource> findResourceById(Long id) {
        return resourceClient.findAllResource();
    }

    @Override
    public void updateState(Long resourceId, String state) {

    }
}
