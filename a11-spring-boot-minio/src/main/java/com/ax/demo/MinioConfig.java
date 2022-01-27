package com.ax.demo;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MinioProp.class)
public class MinioConfig {

    @Autowired
    private MinioProp minioProp;

    /**
     * package com.minio.dto.response;
     * <p>
     * import lombok.AllArgsConstructor;
     * import lombok.Data;
     * import lombok.NoArgsConstructor;
     *
     * @Data
     * @AllArgsConstructor
     * @NoArgsConstructor public class FileUploadResponse {
     * private String urlHttp;
     * <p>
     * private String urlPath;
     * }
     * <p>
     * 获取MinioClient
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioProp.getEndpoint())
                .credentials(minioProp.getAccessKey(), minioProp.getSecretKey())
                .build();
    }

}
