package com.giret.consumer.client;


import com.giret.consumer.model.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@FeignClient(
        name = "resourceClient",
        url = "${api.giret.resource.base-url}"
)
public interface ResourceClient {

    @GetMapping(value = "/api/findAllResource",produces = "application/json")
    List<Resource> findAllResource();




}