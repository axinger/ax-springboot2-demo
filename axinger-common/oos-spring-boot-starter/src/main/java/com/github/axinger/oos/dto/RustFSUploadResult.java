package com.github.axinger.oos.dto;

import lombok.Data;

/**
 * 文件上传返回结果
 */
@Data
public class RustFSUploadResult {
    /// 文件访问URL
    private String url;
    /// 文件名称
    private String name;
}
