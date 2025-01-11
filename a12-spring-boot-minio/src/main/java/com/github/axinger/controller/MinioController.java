package com.github.axinger.controller;

import com.axing.common.minio.service.MinioTemplate;
import com.axing.common.minio.util.FilePathUtil;
import com.axing.common.response.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * <p>
 * minio 前端控制器
 * </p>
 *
 * @author luoyu
 * @since 2018-11-30
 */
@Slf4j
@RestController
public class MinioController {

    @Autowired
    private MinioTemplate minioTemplate;

    /**
     * 上传文件,minio用 InputStream 方式
     */
    @PostMapping(value = "/minio/uploadStream", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<?> uploadByMinio(@RequestParam(value = "file") MultipartFile file,
                                   @RequestParam("bucketName") String bucketName) throws Exception {
        if (file.getSize() < 1) {
            log.warn("文件大小为：0");
            return Result.fail("文件大小为：0");
        }
        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        String contentType = file.getContentType();
        String patchName = FilePathUtil.getFileName(fileName);
        Object upload = minioTemplate.uploadStream(inputStream, bucketName, patchName, contentType);
        return Result.ok(upload);

    }

    /**
     * 上传文件,用 MultipartFile 方式,内部也是走InputStream方式
     */
    @PostMapping(value = "/minio/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<?> uploadFile(@RequestParam(value = "file") MultipartFile file,
                                @RequestParam("bucketName") String bucketName) {
        Object upload = minioTemplate.uploadFile(file, bucketName);
        return Result.ok(upload);
    }

    /**
     * 下载文件
     */
    @GetMapping("/download/{bucket}/{fileName}")
    public void downloadByMinio(HttpServletResponse response, @PathVariable String bucket,
                                @PathVariable String fileName) {
        minioTemplate.download(response, bucket, fileName);
    }

    @GetMapping("/bigDownload/{bucket}/{fileName}")
    public ResponseEntity<StreamingResponseBody> downloadFile(@PathVariable String bucket,
                                                              @PathVariable String fileName) {
        return minioTemplate.downloadStreaming(bucket, fileName);
    }

    /**
     * 获取文件url
     *
     * @return url
     */
    @PostMapping("/minio/fileUrl")
    public Result downloadByMinio(String bucketName, String fileName) {
        String url = minioTemplate.fileUrl(bucketName, fileName);
        return Result.ok(url);
    }

    /**
     * 下载文件到本地
     *
     * @param bucketName
     * @param fileName
     * @return
     */
    @PostMapping("/minio/writeToPath/path")
    public Result downloadByMinio(String bucketName, String fileName, String path) {
        return Result.ok(minioTemplate.writeToPath(bucketName, fileName, path));
    }

}
