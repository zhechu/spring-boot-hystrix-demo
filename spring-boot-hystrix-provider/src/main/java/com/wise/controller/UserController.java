package com.wise.controller;

import com.wise.entity.User;
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

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return new User(id, "zhangsan", "张三", 20, new BigDecimal(112.23));
    }

}
