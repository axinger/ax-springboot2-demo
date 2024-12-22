package com.github.axinger;

import cn.hutool.core.annotation.Alias;
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
    @Alias("主键")
    private Integer id;

    /**
     * 姓名
     */
    @Alias("姓名")
    private String name;

    /**
     * 性别
     */
    @Alias("性别")
    private String gender;
}
