package com.axing.common.util.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import javax.annotation.Nullable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Spring Boot @PropertySource 加载指定yaml配置文件获取不到配置的解决方法
 */
public class YamlAndPropertySourceFactory extends DefaultPropertySourceFactory {
    // @Override
    // public PropertySource<?> createPropertySource(@Nullable String name, EncodedResource resource) throws IOException {
    //     Resource resourceResource = resource.getResource();
    //     if (!resourceResource.exists()) {
    //         return new PropertiesPropertySource(null, new Properties());
    //     } else if (resourceResource.getFilename().endsWith(".yml") || resourceResource.getFilename().endsWith(".yaml")) {
    //         List<PropertySource<?>> sources = new YamlPropertySourceLoader().load(resourceResource.getFilename(), resourceResource);
    //         return sources.get(0);
    //     }
    //     return super.createPropertySource(name, resource);
    // }

    @Override
    public PropertySource<?> createPropertySource(@Nullable String name, EncodedResource resource) throws IOException {
        Properties properties = loadYamlIntoProperties(resource);
        String sourceName = name != null ? name : resource.getResource().getFilename();
        return new PropertiesPropertySource(sourceName, properties);
    }

    private Properties loadYamlIntoProperties(EncodedResource resource) throws FileNotFoundException {
        try {
            YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
            factory.setResources(resource.getResource());
            factory.afterPropertiesSet();
            return factory.getObject();
        } catch (IllegalStateException e) {
            // for ignoreResourceNotFound
            Throwable cause = e.getCause();
            if (cause instanceof FileNotFoundException) {
                throw (FileNotFoundException) e.getCause();
            }
            throw e;
        }
    }

}
