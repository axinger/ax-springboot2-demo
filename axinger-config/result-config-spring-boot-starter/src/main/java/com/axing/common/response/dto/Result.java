package com.axing.common.response.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Schema(title = "提示信息", description = "错误信息")
    private String msg = "";

    @Schema(title = "详情", description = "详情信息")
    private Object details = "";

    @Schema(title = "返回数据", description = "数据")
    private T data;


    public static <T> Result<T> ok(T data) {
        return Result.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    public static <T> Result<T> ok() {
        return Result.ok("");
    }

    public static <T> Result<T> ok(String msg) {
        return Result.<T>builder()
                .success(true)
                .msg(msg)
                .build();

    }

    public static <T> Result<T> fail(String msg, Object details) {
        return Result.<T>builder()
                .success(false)
                .msg(msg)
                .details(details)
                .build();
    }

    public static <T> Result<T> fail(String msg) {
        return Result.<T>builder()
                .success(false)
                .msg(msg)
                .build();
    }

    public static <T> Result<T> fail() {
        return Result.fail("");
    }

}
