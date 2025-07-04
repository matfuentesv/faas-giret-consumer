package com.giret.consumer.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.giret.consumer.model.Resource;


@FeignClient(
        name = "resourceClient",
        url = "${api.giret.resource.base-url}"
)
public interface ResourceClient {

    @PutMapping(value = "/api/updateResourceByState/{id}/{state}",produces = "application/json")
    Resource updateResourceByState(@PathVariable ("id")Long id,
                                   @PathVariable ("state")String state);




}