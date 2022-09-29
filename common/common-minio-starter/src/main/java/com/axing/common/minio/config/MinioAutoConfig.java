package com.axing.common.minio.config;

import com.axing.common.minio.bean.MinioProperties;
import com.axing.common.minio.service.MinioService;
import com.axing.common.minio.service.impl.MinioServiceImpl;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 *
 * @author luoyu
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({
        MinioProperties.class
})
public class MinioAutoConfig {

    @Autowired
    private MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }

    @Bean
    @ConditionalOnMissingBean(MinioService.class)
    public MinioService minioService() {
        return new MinioServiceImpl();
    }

}
