package ax.com.dubbo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Demo13DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo13DubboProviderApplication.class, args);
    }

}
