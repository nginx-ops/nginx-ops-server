// package io.github.nginx.ops.server.comm.config;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.hibernate.validator.BaseHibernateValidatorConfiguration;
// import org.hibernate.validator.HibernateValidator;
// import org.springframework.context.MessageSource;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.validation.Validator;
// import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
// import org.springframework.web.servlet.LocaleResolver;
// import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
//
// import java.util.Locale;
//
// /**
//  * 异常国际化
//  *
//  * @author lihao3
//  */
// @Slf4j
// @Configuration
// @RequiredArgsConstructor
// public class ValidatorConfig {
//
//   private final MessageSource messageSource;
//
//   /**
//    * 设置默认语言
//    *
//    * @return
//    */
//   @Bean
//   public LocaleResolver localeResolver() {
//     AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
//     acceptHeaderLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
//     return acceptHeaderLocaleResolver;
//   }
//
//   @Bean
//   public Validator getValidator() {
//     LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//     // 国际化
//     validator.setValidationMessageSource(messageSource);
//     // 设置使用 HibernateValidator 校验器
//     validator.setProviderClass(HibernateValidator.class);
//     // 快速失败
//     validator
//         .getValidationPropertyMap()
//         .put(BaseHibernateValidatorConfiguration.FAIL_FAST, Boolean.TRUE.toString());
//     // 加载配置
//     validator.afterPropertiesSet();
//     return validator;
//   }
// }
