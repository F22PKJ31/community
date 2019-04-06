package com.f22pkj31.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.f22pkj31.community.mapper")
public class NewsProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsProviderApplication.class, args);
    }

}
