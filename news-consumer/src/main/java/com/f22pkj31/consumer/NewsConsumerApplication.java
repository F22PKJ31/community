package com.f22pkj31.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class NewsConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsConsumerApplication.class, args);
    }

}
