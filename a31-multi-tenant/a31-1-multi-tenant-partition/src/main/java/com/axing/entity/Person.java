package com.axing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.TenantId;

@Entity // 1
@Data
public class Person {
    // GenerationType.IDENTITY：主键由数据库自动生成（主要是自动增长型）。
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 3
    private Long id;
    /**
     * 使用@TenantId注解的属性tenantId作为分区数据多租户的的区分标识。
     */
    @TenantId // 4
    private String tenantId;
    private String name;
    private Integer age;
}
