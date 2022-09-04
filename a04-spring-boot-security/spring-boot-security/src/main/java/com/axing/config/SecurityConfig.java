package com.axing.config;

import cn.hutool.core.collection.ListUtil;
import com.axing.component.DynamicSecurityFilter;
import com.axing.component.JwtAuthenticationTokenFilter;
import com.axing.component.RestAuthenticationEntryPoint;
import com.axing.component.RestfulAccessDeniedHandler;
import com.axing.service.DynamicSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * SpringSecurity 5.4.x以上新用法配置
 * 为避免循环依赖，仅用于配置HttpSecurity
 * Created by macro on 2022/5/19.
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private DynamicSecurityService dynamicSecurityService;
    @Autowired
    private DynamicSecurityFilter dynamicSecurityFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();
        //不需要保护的资源路径允许访问
//        for (String url : ignoreUrlsConfig.getUrls()) {
//            registry.antMatchers(url).permitAll();
//        }
        //允许跨域请求的OPTIONS请求
        registry.antMatchers(HttpMethod.OPTIONS)
                .permitAll();
        httpSecurity.csrf()// 由于使用的是JWT，我们这里不需要csrf
                .disable()
                .sessionManagement()// 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
                .authenticated();
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
        //有动态权限配置时添加动态权限校验过滤器
        if (dynamicSecurityService != null) {
            registry.and().addFilterBefore(dynamicSecurityFilter, FilterSecurityInterceptor.class);
        }
        return httpSecurity.build();
    }


    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        List<String> list = ignoreUrlsConfig.getUrls();



//        List<String> list =    ListUtil.of( "/",
//                            "/swagger-ui.html",
//                            "/swagger-ui/",
//                            "/*.html",
//                            "/favicon.ico",
//                            "/**/*.html",
//                            "/**/*.css",
//                            "/**/*.js",
//                            "/swagger-resources/**",
//                            "/v3/api-docs/**");

        String[] objects = list.toArray(new String[list.size()]);
        return web -> web.ignoring().antMatchers(HttpMethod.GET, // Swagger的资源路径需要允许访问
                        objects
                )
                // 忽略常见的静态资源路径
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());

//        return web -> {
//
//            web.ignoring().antMatchers(HttpMethod.GET, // Swagger的资源路径需要允许访问
//                            "/",
//                            "/swagger-ui.html",
//                            "/swagger-ui/",
//                            "/*.html",
//                            "/favicon.ico",
//                            "/**/*.html",
//                            "/**/*.css",
//                            "/**/*.js",
//                            "/swagger-resources/**",
//                            "/v3/api-docs/**"
//                    )
//                    // 忽略常见的静态资源路径
//                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
//            ;
//        };
    }

}
