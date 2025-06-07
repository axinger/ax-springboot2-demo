package com.github.axinger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "axinger.user.username=张三,李四"
})
public class A01MVCApplicationTest {


    @Value("#{'${axinger.user.username:}'.split(',')}")
    private List<String> usernameList;

    @Value("${axinger.user.username}")
    private List<String> usernameList2;

    @Test
    public void test1() {

        System.out.println("usernameList = " + usernameList);
        System.out.println("usernameList2 = " + usernameList2);
    }


}
