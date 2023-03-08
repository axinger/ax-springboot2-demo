package com.axing.demo.bean;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Data
@Configuration
@ConfigurationProperties(prefix = "show")
public class HumitureRuleProperties {

    private HumidityDto humidity;

    // @Data
    // public static class HumidityDto {
    //     private double min;
    //     private double max;
    // }

    public record HumidityDto(double min, double max) {

    }
}
