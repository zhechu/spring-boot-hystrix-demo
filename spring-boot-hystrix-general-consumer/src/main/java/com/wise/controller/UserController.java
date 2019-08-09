package com.wise.controller;

import com.wise.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    public User findById(@PathVariable Long id) {
        String url = String.format("http://%s/users/{id}", springBootHystrixProviderName);
        User user = this.restTemplate.getForObject(url, User.class, id);
        return user;
    }

}
