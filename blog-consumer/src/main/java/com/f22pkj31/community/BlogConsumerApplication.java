package com.f22pkj31.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BlogConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogConsumerApplication.class, args);
    }

}
