package com.axing.common.response.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

/**
 * 全局统一返回结果类
 *
 * @author xing
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "返回体", description = "请求返回结果")
public class Result<T> {

    @Schema(title = "状态码", description = "200 正常;")
    private Integer code;

    @Schema(title = "返回消息", description = "code!=200,会有")
    private String msg;

    @Schema(title = "返回数据", description = "泛型数据,类型不固定")
    private T data;


    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> build(ResultCodeEnum resultCodeEnum, T body) {
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMsg(resultCodeEnum.getMessage());
        return result;
    }

    public static <T> Result<T> build(Integer code, T body, String message) {
        Result<T> result = build(body);
        result.setCode(code);
        result.setMsg(message);
        return result;
    }

    public static <T> Result<T> build(Integer code, String message) {
        Result<T> result = build(null);
        result.setCode(code);
        result.setMsg(message);
        return result;
    }

    /**
     * 成功
     *
     * @param data 数据
     * @param <T>  泛型
     * @return 结果
     */
    public static <T> Result<T> ok(T data) {
        return build(ResultCodeEnum.SUCCESS, data);
    }

    public static <T> Result<T> ok() {
        return Result.ok(null);
    }

    public static <T> Result<T> fail(T data) {
        return build(ResultCodeEnum.FAIL, data);
    }

    public static <T> Result<T> fail(ResultCodeEnum resultCodeEnum, T data) {
        return build(resultCodeEnum, data);
    }


    public static <T> Result<T> fail(String message) {
        return build(ResultCodeEnum.FAIL.getCode(), message);
    }

    public static <T> Result<T> fail() {
        return Result.fail(null);
    }

    public Result<T> message(String msg) {
        this.setMsg(msg);
        return this;
    }

    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * 不序列化
     *
     * @return 状态是否等于200
     */
    @Transient
    @JsonIgnore
    public boolean isSuccess() {
        return this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue();
    }
}
