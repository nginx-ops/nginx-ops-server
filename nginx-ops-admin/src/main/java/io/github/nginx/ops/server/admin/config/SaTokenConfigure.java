package io.github.nginx.ops.server.admin.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lihao3
 * @date 2022/9/16 12:25
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

  /**
   * 注册 Sa-Token 拦截器，打开注解式鉴权功能
   *
   * @param registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
    registry
        .addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
        .addPathPatterns("/**")
        .excludePathPatterns("/doLogin", "/captcha")
        .excludePathPatterns(
            "/swagger-resources/**",
            "/webjars/**",
            "/v2/**",
            "/swagger-ui.html/**",
            "/doc.html/**",
            "/error",
            "/favicon.ico");
  }
}
