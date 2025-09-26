package com.github.axinger.controller;

import com.github.axinger.oos.service.OssTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/api/oss")
public class OssController {

    @Autowired
    private OssTemplate ossTemplate;

    /**
     * 浏览器访问这个 URL 就能直接下载文件
     */
    @GetMapping("/download/{bucketName}/{objectName:.+}")
    public void downloadFile(
            @PathVariable String bucketName,
            @PathVariable String objectName,
            HttpServletResponse response) {
        try (ResponseInputStream<GetObjectResponse> objectStream = ossTemplate.getObject(bucketName, objectName)) {

            // 获取元信息
            GetObjectResponse metadata = objectStream.response();

            // 设置响应头
            response.setContentType(metadata.contentType());
            response.setContentLengthLong(metadata.contentLength());

            // 推荐设置：让浏览器下载而不是预览（除非你想预览图片/PDF）
            String fileName = Paths.get(objectName).getFileName().toString();
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + URLEncoder.encode(fileName, StandardCharsets.UTF_8) + "\"");

            // 将 S3 的文件流写入 HTTP 响应
            StreamUtils.copy(objectStream, response.getOutputStream());
            response.flushBuffer();

            log.info("File downloaded via HTTP: {}/{}", bucketName, objectName);
        } catch (Exception e) {
            log.error("Download failed: {}/{}", bucketName, objectName, e);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                response.getWriter().write("File not found or error: " + e.getMessage());
            } catch (IOException ignored) {
            }
        }
    }
}
