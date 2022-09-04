package com.axing.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 后台资源表
 * </p>
 *
 * @author macro
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "UmsResource对象", description = "后台资源表")
@Builder
public class UmsResource {

    private Long id;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "资源名称")
    private String name;

    @Schema(title = "资源URL")
    private String url;

    @Schema(title = "描述")
    private String description;

    @Schema(title = "资源分类ID")
    private Long categoryId;

}
