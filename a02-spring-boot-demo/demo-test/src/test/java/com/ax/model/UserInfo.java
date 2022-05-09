package com.ax.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName UserInfo.java
 * @description TODO
 * @createTime 2022年03月21日 13:40:00
 */

@Data
@Builder
public class UserInfo {
    private Long uid;
    private Long uid2;
    private Integer gender;
}
