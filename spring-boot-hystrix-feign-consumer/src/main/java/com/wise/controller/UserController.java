package com.wise.controller;

import com.wise.entity.User;
import com.wise.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 用户控制器
 */
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }

}
