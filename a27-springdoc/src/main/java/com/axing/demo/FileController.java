package com.axing.demo;

import com.axing.common.response.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName FileController.java
 * @description TODO
 * @createTime 2022年07月23日 21:27:00
 */

@Tag(name = "文件控制器", description = "文件")
@RestController
public class FileController {

    @Operation(summary = "文件上传返回url", description = "这个方式可以")
    @PostMapping(name = "/uplupload1oad", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> upload1(@RequestPart(value = "file") MultipartFile file, @RequestPart("name") String name) {
        System.out.println("file = " + file);
        System.out.println("name = " + name);
        return Result.ok();
    }

    //form表单提交  Content type 为  multipart/form-data 时，不需要使用@RequestBody注解，去掉即可
    @Operation(summary = "文件上传返回url", description = "这个目前不可以")
    @PostMapping(name = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> upload(@RequestPart(value = "file") MultipartFile file, @RequestPart("map") Map map) {
        System.out.println("file = " + file);
        System.out.println("map = " + map);
        return Result.ok();
    }

}
