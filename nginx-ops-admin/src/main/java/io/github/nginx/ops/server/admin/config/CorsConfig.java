package io.github.nginx.ops.server.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// 实现 WebMvcConfigurer 接口
public class CorsConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        // 放行哪些原始域
        .allowedOrigins("*")
        .allowedHeaders("*")
        // 是否发送Cookie
        .allowCredentials(true)
        .allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH")
        .maxAge(3600);
  }
}
