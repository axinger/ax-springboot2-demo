package com.github.axinger.jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Person {

    private String id;
    private String name;

    private Map<String, Object> otherProperties = new HashMap<>();

    @JsonAnySetter
    public void setOtherProperty(String key, Object value) {
        otherProperties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getOtherProperties() {
        return otherProperties;
    }
}
