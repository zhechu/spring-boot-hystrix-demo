package com.wise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 启动类
 * 监控示例：http://127.0.0.1:8082/actuator/health
 */
@SpringBootApplication
@EnableCircuitBreaker
public class GeneralConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneralConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced // 负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
