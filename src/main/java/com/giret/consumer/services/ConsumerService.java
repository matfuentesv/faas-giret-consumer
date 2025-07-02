package com.giret.consumer.services;

import com.giret.consumer.model.Resource;

import java.util.List;

public interface ConsumerService {

    List<Resource> findResourceById(Long id);
    void updateState (Long resourceId,String state );
}
