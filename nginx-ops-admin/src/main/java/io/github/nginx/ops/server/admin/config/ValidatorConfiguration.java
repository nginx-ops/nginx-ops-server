package io.github.nginx.ops.server.admin.config;

import org.hibernate.validator.BaseHibernateValidatorConfiguration;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author lihao3
 * @date 2022/9/16 12:55
 */
@Configuration
public class ValidatorConfiguration {

  @Bean
  public LocalValidatorFactoryBean validator(AutowireCapableBeanFactory springFactory) {
    LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
    MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory();
    factoryBean.setMessageInterpolator(interpolatorFactory.getObject());
    // 快速失败
    factoryBean
        .getValidationPropertyMap()
        .put(BaseHibernateValidatorConfiguration.FAIL_FAST, Boolean.TRUE.toString());
    return factoryBean;
  }
}
