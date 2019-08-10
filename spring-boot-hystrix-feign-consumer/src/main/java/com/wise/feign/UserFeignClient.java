package com.wise.feign;

import com.wise.entity.User;
import feign.Logger;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

/**
 * 用户远程调用
 */
@FeignClient(
        name = "${application.service.spring-boot-hystrix-provider.name}",
        url = "${application.service.spring-boot-hystrix-provider.url}",
        fallbackFactory = UserFeignClientFallbackFactory.class)
public interface UserFeignClient {

    @GetMapping("/users/{id}")
    User findById(@PathVariable("id") Long id);

}

@Component
class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable throwable) {

        return new UserFeignClient() {

            @Override
            public User findById(Long id) {
                throwable.printStackTrace();
                return new User(id, "默认用户", "默认用户", 0, new BigDecimal(1));
            }
        };
    }

}