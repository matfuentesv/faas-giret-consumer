package com.giret.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.giret.consumer.client")
public class SpringBootAzureApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAzureApp.class, args);
    }
}
