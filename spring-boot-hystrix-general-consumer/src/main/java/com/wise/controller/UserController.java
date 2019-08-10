package com.wise.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wise.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * 用户控制器
 */
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${application.service.spring-boot-hystrix-provider.name}")
    private String springBootHystrixProviderName;

    @GetMapping("/{id}")
    // 熔断降级
    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public User findById(@PathVariable Long id) {
        String url = String.format("http://%s/users/{id}", springBootHystrixProviderName);
        User user = this.restTemplate.getForObject(url, User.class, id);
        return user;
    }

    /**
     * 熔断降级回调方法
     * @param id
     * @return
     */
    public User findByIdFallback(Long id, Throwable throwable) {
        throwable.printStackTrace();
        return new User(id, "默认用户", "默认用户", 0, new BigDecimal(1));
    }

}
