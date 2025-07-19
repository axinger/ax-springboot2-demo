package com.axing.common.response.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * 全局统一返回结果类
 *
 * @author xing
 * Result 和 T用title,name不要同时使用
 * 避免bug
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Result", description = "请求返回结果")
public class Result<T> implements Serializable {

    @Schema(title = "是否成功", description = "成功：true；失败：false")
    private Boolean success = false;

    @Schema(title = "状态码", description = "2xx成功")
    private Integer code;

    @Schema(title = "提示信息", description = "错误信息")
    private String msg = "";

    @Schema(title = "详情", description = "详情信息")
    private Object details = "";

    @Schema(title = "返回数据", description = "数据")
    private T data;

    public static <T> Result<T> ok(T data) {
        return Result.<T>builder()
                .code(200)
//                .success(true)
                .data(data)
                .build();
    }

    public static <T> Result<T> ok() {
        return Result.ok("");
    }

    public static <T> Result<T> ok(String msg) {
        return Result.<T>builder()
                .code(200)
//                .success(true)
                .msg(msg)
                .build();
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        return Result.<T>builder()
                .code(code)
//                .success(false)
                .msg(msg)
                .build();
    }

    public static <T> Result<T> fail(String msg, Object details) {
        return Result.<T>builder()
                .code(400)
//                .success(false)
                .msg(msg)
                .details(details)
                .build();
    }

    public static <T> Result<T> fail(String msg) {
        return Result.<T>builder()
                .code(400)
//                .success(false)
                .msg(msg)
                .build();
    }

    public static <T> Result<T> fail() {
        return Result.fail("");
    }

    public static <T> Result<T> success() {
        return Result.ok("");
    }

    public static <T> Result<T> success(String msg) {
        return Result.<T>builder()
                .code(200)
//                .success(true)
                .msg(msg)
                .build();
    }

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .code(200)
//                .success(true)
                .data(data)
                .build();
    }

    public Boolean getSuccess() {
        return (code != null && code >= 200 && code < 300);
    }

    @SneakyThrows
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
