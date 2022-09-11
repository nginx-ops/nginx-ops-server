package io.github.nginx.ops.server.comm.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class I18nConfig implements WebMvcConfigurer {

  private final MessageSource messageSource;

  /**
   * 设置默认语言
   *
   * @return
   */
  @Bean
  public LocaleResolver localeResolver() {
    AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
    acceptHeaderLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
    return acceptHeaderLocaleResolver;
  }

  /** 使用自定义LocalValidatorFactoryBean， 设置Spring国际化消息源 */
  @Bean
  @Override
  public Validator getValidator() {
    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    // 仅兼容Spring Boot spring.messages设置的国际化文件和原hibernate-validator的国际化文件
    // 不支持resource/ValidationMessages.properties系列
    bean.setValidationMessageSource(this.messageSource);
    return bean;
  }
}
