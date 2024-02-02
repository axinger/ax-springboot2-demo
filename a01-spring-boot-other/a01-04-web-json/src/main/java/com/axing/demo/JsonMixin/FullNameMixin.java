package com.axing.demo.JsonMixin;

import com.axing.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.jackson.JsonMixin;

@JsonMixin(User.class) // 1
@Data
public abstract class FullNameMixin {
    @JsonProperty("fullName")  // 2
    String name; // 3
}
