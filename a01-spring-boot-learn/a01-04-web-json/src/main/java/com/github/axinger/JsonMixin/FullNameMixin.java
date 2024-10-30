package com.github.axinger.JsonMixin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.axinger.entity.User;
import lombok.Data;
import org.springframework.boot.jackson.JsonMixin;

@JsonMixin(User.class) // 1
@Data
public abstract class FullNameMixin {
    @JsonProperty("fullName")  // 2
    String name; // 3
}
