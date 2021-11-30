package com.ax.demo.dto;

import lombok.*;

import javax.validation.constraints.*;


/**
 * @author xing
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StudentDto {

    @NotBlank(message = "姓名不能空")
    private String name;

    @NotNull(message = "年龄不能空")
    @Min(value = 1, message = "年龄有误！")
    @Max(value = 120, message = "年龄有误！")
    private Integer age;

    /**
     * 邮箱
     */
    @Email(message = "邮箱有误！")
    private String email;
}
