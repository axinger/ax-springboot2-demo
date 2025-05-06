package com.github.axinger.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;


@Data
/**
 * 生成静态方法,只有 @NonNull 注解的,指定参数的静态构造方法
 */
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(force = true)
public class Address {

    @NonNull // lombok.NonNull, 可以校验,初始化
    @NotEmpty // 只能是请求参数校验
    private String houseNumber;

    @NonNull
    private String street;

    private String city;

    private Area area;

    @NonNull
    private String country;

    public static void test(@Validated Address address) {

    }

    public static void main(String[] args) {
        // Address address = Address.of("1", "2", "3");
        // System.out.println("address = " + address);
        Address address1 = new Address();
        // address1.setHouseNumber(null);
        address1.setHouseNumber("");
        System.out.println("address1 = " + address1);

    }
}
