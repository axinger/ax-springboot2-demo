package com.github.axinger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.axinger.jackson.Person;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class JacksonTests {

    @SneakyThrows
    @Test
    public void test1() {
        ObjectMapper mapper = new ObjectMapper();

        // 反序列化：JSON -> Java对象
        String json = "{\"id\":\"1\",\"name\":\"Alice\",\"age\":30,\"city\":\"New York\"}";
        Person person = mapper.readValue(json, Person.class);
        System.out.println(person); // 输出: Person(id=1, name=Alice, otherProperties={age=30, city=New York})

        // 序列化：Java对象 -> JSON
        person.setOtherProperty("gender", "female");
        String outputJson = mapper.writeValueAsString(person);
        System.out.println(outputJson); // 输出: {"id":"1","name":"Alice","age":30,"city":"New York","gender":"female"}
    }
}
