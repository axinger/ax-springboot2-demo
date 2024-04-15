package com.github.axinger.controller;

import com.axing.common.minio.service.MinioService;
import com.axing.common.minio.util.FilePathUtil;
import com.axing.common.response.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    private MinioService minioService;

    /**
     * 上传文件,用 InputStream 方式
     */
    @PostMapping(value = "/minio/uploadStream", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result uploadByMinio(@RequestParam(value = "file") MultipartFile file,
                                @RequestParam("bucketName") String bucketName) throws Exception {
        if (file.getSize() < 1) {
            log.warn("文件大小为：0");
            return Result.fail("文件大小为：0");
        }
        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        String contentType = file.getContentType();
        String patchName = FilePathUtil.getFileName(fileName);
        Object upload = minioService.uploadStream(inputStream, bucketName, patchName, contentType);
        return Result.ok(upload);

    }

    /**
     * 上传文件,用 MultipartFile 方式,内部也是走InputStream方式
     */
    @PostMapping(value = "/minio/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result uploadFile(@RequestParam(value = "file") MultipartFile file,
                             @RequestParam("bucketName") String bucketName) {
        Object upload = minioService.uploadFile(file, bucketName);
        return Result.ok(upload);
    }

    /**
     * 下载文件
     */
    @PostMapping("/minio/download")
    public void downloadByMinio(HttpServletResponse response, String bucketName, String fileName) {
        minioService.download(response, bucketName, fileName);
    }

    @PostMapping("/minio/fileUrl")
    public Result downloadByMinio(String bucketName, String fileName) {
        String url = minioService.fileUrl(bucketName, fileName);
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
        return Result.ok(minioService.writeToPath(bucketName, fileName, path));
    }

}
