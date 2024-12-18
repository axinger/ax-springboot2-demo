package com.github.axinger;

import lombok.*;

import java.io.Serializable;

/**
 * 用户
 * @author zlc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;
}
