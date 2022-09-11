// package io.github.nginx.ops.server.comm.config;
//
// import lombok.RequiredArgsConstructor;
// import org.springframework.boot.validation.MessageInterpolatorFactory;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.support.ResourceBundleMessageSource;
// import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
// import org.springframework.web.servlet.LocaleResolver;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//
// import java.util.Locale;
//
// @Configuration
// @RequiredArgsConstructor
// public class I18nConfig implements WebMvcConfigurer {
//
//   /** 国际化的参数名 */
//   private static final String LANGUAGE_PARAM_NAME = "lang";
//
//   private final ResourceBundleMessageSource resourceBundleMessageSource;
//
//   @Bean
//   public LocaleResolver localeResolver() {
//     SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//     // 指定默认语言为中文
//     localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
//     return localeResolver;
//   }
//
//   @Bean
//   public LocalValidatorFactoryBean localValidatorFactoryBean() {
//     LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
//     MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory();
//     factoryBean.setMessageInterpolator(interpolatorFactory.getObject());
//     // 设置快速失败，Hibernate 验证框架默认验证所有字段设置的所有规则，并返回错误集合。
//     // 快速失败则是只要验证时出现一个错误，立马返回，不执行后面的验证规则
//     factoryBean.getValidationPropertyMap().put("hibernate.validator.fail_fast", "true");
//     // 为Validator配置国际化
//     factoryBean.setValidationMessageSource(resourceBundleMessageSource);
//     return factoryBean;
//   }
//
// }
