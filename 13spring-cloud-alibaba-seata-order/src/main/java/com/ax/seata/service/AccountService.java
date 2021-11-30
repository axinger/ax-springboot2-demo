package com.ax.seata.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author xing
 * @description 针对表【t_order】的数据库操作Service
 * @createDate 2021-12-19 00:46:12
 */
@FeignClient(value = "demo13spring-cloud-alibaba-seata-account",url = "http://localhost:8072")
@Component
public interface AccountService {

    /**
     * 减少余额
     */
    @GetMapping(value = "/account/decrease")
    Object decrease(@RequestParam(value = "userId") Long userId,
                    @RequestParam(value = "money") BigDecimal money);

}
