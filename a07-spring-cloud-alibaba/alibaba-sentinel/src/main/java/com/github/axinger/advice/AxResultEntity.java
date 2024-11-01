package com.github.axinger.advice;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author axing
 */
@Data
@ToString
public class AxResultEntity<T> implements Serializable {

    //    private boolean state;
    private Integer code;
    private T body;
    private String msg;
    /**
     * 忽略序列化
     */
////    @JSONField(serialize=false)  transient关键字只能修饰变量，而不能修饰方法和类。
    private transient AxResultStateEnum stateEnum;

    //        @JSONField(serialize=false)
//    private  AxResultStateEnum stateEnum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //@DatetimeFormat是将String转换成Date，一般前台给后台传值时用
    private Date date;

    public AxResultEntity() {
    }

    public AxResultEntity(Integer code, T body) {
        this.code = code;
        this.body = body;
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    @NotNull
    public static <T> AxResultEntity<T> success(AxResultStateEnum resultStatus, T data) {
        if (resultStatus == null) {
            return new AxResultEntity<>(AxResultStateEnum.SUCCESS.value(), data);
        }
        return new AxResultEntity<>(resultStatus.value(), data);
    }


    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    @NotNull
    public static <T> AxResultEntity<T> Success(T data) {
        return new AxResultEntity<T>(AxResultStateEnum.SUCCESS.value(), data);
    }

    public AxResultStateEnum getStateEnum() {
        return stateEnum;
    }


    public void setStateEnum(@NotNull AxResultStateEnum stateEnum) {
        this.stateEnum = stateEnum;
        this.code = stateEnum.value();
        this.msg = stateEnum.reasonPhrase();
    }


}
