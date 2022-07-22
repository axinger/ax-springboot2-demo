package com.ax.demo;

import com.axing.common.response.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "文件相关接口")
@Slf4j
@RestController
@RequestMapping(value = "/")
public class FileController {

    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private MinioConfig prop;

    @Operation(summary = "test1")
    @GetMapping("/test1")
    public Map test1() {
        return new HashMap<String, Object>() {
            {
                put("name", 1);
            }
        };
    }

    @Operation(summary = "test2")
    @GetMapping("/test2")
    public Map test2() {
        int i = 1 / 0;

        return new HashMap<String, Object>() {
            {
                put("name", 1);
            }
        };
    }

    @Operation(summary = "查看存储bucket是否存在")
    @GetMapping("/bucketExists")
    public Result bucketExists(String bucketName) {
        return Result.ok(minioUtil.bucketExists(bucketName));
    }

    @Operation(summary = "创建存储bucket")
    @GetMapping("/makeBucket")
    public Result makeBucket(String bucketName) {
        minioUtil.makeBucket(bucketName);
        return Result.ok();
    }

    @Operation(summary = "删除存储bucket")
    @GetMapping("/removeBucket")
    public Result removeBucket(String bucketName) {
        minioUtil.removeBucket(bucketName);
        return Result.ok();
    }

    @Operation(summary = "获取全部bucket")
    @GetMapping("/getAllBuckets")
    public Result<List<String>> getAllBuckets() {
        return Result.ok(minioUtil.getAllBuckets());
    }

    @Operation(summary = "文件上传返回url")
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam(value = "file") MultipartFile file) {
        String objectName = minioUtil.upload(file);
        if (null != objectName) {
            return Result.ok(prop.getEndpoint() + "/" + prop.getBucketName() + "/" + objectName);
        }
        return Result.fail();
    }

    @Operation(summary = "图片/视频预览")
    @GetMapping("/preview")
    public Result preview(@RequestParam("fileName") String fileName) {
        return Result.ok(minioUtil.preview(fileName));
    }

    @Operation(summary = "文件下载")
    @GetMapping("/download")
    public Result download(@RequestParam("fileName") String fileName, HttpServletResponse res) {
        minioUtil.download(fileName, res);
        return Result.ok();
    }

    @Operation(summary = "删除文件", description = "根据url地址删除文件")
    @PostMapping("/delete")
    public Result remove(String url) {
        String objName = url.substring(url.lastIndexOf(prop.getBucketName() + "/") + prop.getBucketName().length() + 1);
        minioUtil.remove(objName);
        return Result.ok();
    }

}

