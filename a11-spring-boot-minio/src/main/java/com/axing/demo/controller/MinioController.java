package com.axing.demo.controller;

import com.axing.common.minio.service.MinioService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
     * 上传文件
     */
    @PostMapping(value = "/minio/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result uploadByMinio(@RequestParam(value = "file") MultipartFile file,
                                @RequestParam("bucketName") String bucketName) throws Exception {
        if (file.getSize() < 1) {
            log.warn("文件大小为：0");
            return Result.fail("文件大小为：0");
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        InputStream inputStream = file.getInputStream();
        String contentType = file.getContentType();
        String patchName = this.getPath() + suffix;
        String upload = minioService.upload(inputStream, bucketName, patchName, contentType);
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
     * 文件路径
     *
     * @return 返回上传路径
     */
    private String getPath() {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date()) + "/" + uuid;
    }

}
