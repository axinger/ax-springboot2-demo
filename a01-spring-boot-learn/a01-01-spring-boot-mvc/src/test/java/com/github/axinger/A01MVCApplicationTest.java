package com.github.axinger;

import com.github.axinger.model.bean.PersonProperties;
import com.github.axinger.model.bean.UserProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class A01MVCApplicationTest {


    @Autowired
    private PersonProperties personProperties;
    @Autowired
    private UserProperties userProperties;

    @Test
    public void test() {
        System.out.println("personProperties = " + personProperties);
    }


    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void test2() {


        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("token", "jim");
        headers.add("token", "jim");


        HttpEntity<?> httpEntity = new HttpEntity<>(headers);


        String url = UriComponentsBuilder.fromPath("/get/login")
                .queryParam("username", "jim")
                .queryParam("password", "123456")
                .encode()                    // 自动处理 URL 编码
                .toUriString();

        ResponseEntity<Object> response = testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
        });

        Object body1 = response.getBody();
        System.out.println("body1 = " + body1);

        String str = testRestTemplate.getForObject(url, String.class);
        System.out.println("str = " + str);
    }

}
