package com.ax.master.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @author axing
 * implements Serializable redis 需要序列化
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IpLog implements Serializable {

    // 采用的redis序列化方式是默认的jdk序列化。所以数据库的查询对象实体需要实现Serializable接口。
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSS")// 页面写入数据库时格式
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")// 数据库导出页面时json格式化
    private Date loginTime;

//    /**测试时间格式化**/
//    @JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
//    private Date loginTimeStr;

//    public Date getLoginTimeStr() {
//        return loginTime;
//    }


    private String ip;
    private Integer loginState;
    private Integer userType;
    private Integer userinfoId;


}
