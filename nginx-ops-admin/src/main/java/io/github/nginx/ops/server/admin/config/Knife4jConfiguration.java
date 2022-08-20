package io.github.nginx.ops.server.admin.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author lihao3
 * @since 2022/4/25
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

  @Bean(value = "defaultApi2")
  public Docket defaultApi2() {
    Docket docket =
        new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(
                new ApiInfoBuilder()
                    .title("nginx-ops")
                    .description("nginx-ops-module-conf")
                    .termsOfServiceUrl("https://github.com/pearstack/nginx-ops")
                    .contact("lihao3@foxmail.com")
                    .version("1.0.0")
                    .build())
            .select()
            // 这里指定Controller扫描包路径
            .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
            .paths(PathSelectors.any())
            .build();
    return docket;
  }
}
