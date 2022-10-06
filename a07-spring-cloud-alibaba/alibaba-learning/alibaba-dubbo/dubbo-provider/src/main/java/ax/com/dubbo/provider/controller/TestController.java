package ax.com.dubbo.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName TestController.java
 * @Description TODO
 * @createTime 2021年12月18日 22:02:00
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public Object test() {
        System.out.println("进入了ConsumerController.............");
        return "TestController+test";
    }

}
