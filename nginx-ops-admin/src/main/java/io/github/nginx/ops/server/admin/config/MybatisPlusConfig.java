package io.github.nginx.ops.server.admin.config;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author lihao3
 * @date 2022/8/11 11:09
 */
@Slf4j
@Configuration
public class MybatisPlusConfig implements MetaObjectHandler {

  public static void main(String[] args) {
    System.out.println("\"sys\".equals(\"nginx\") = " + "sys".equals("nginx"));
  }

  @Override
  public void insertFill(MetaObject metaObject) {
    log.debug("start insert fill ....");
    DateTime dateTime = new DateTime();
    // UserInfo userInfo = UserInfoUtils.getUserInfo();
    // this.strictInsertFill(
    //     metaObject, "createBy", String.class, userInfo.getSysUser().getLoginName());
    // this.strictInsertFill(
    //     metaObject, "updateBy", String.class, userInfo.getSysUser().getLoginName());
    // this.strictInsertFill(
    //     metaObject, "tenantId", String.class, userInfo.getSelectedSetting().getId());
    this.strictInsertFill(metaObject, "createBy", String.class, "test");
    this.strictInsertFill(metaObject, "updateBy", String.class, "test");
    this.strictInsertFill(metaObject, "tenantId", String.class, "test");
    this.strictInsertFill(metaObject, "updateTime", Date.class, dateTime);
    this.strictInsertFill(metaObject, "createTime", Date.class, dateTime);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    log.debug("start update fill ....");
    DateTime dateTime = new DateTime();
    // UserInfo userInfo = UserInfoUtils.getUserInfo();
    this.strictInsertFill(metaObject, "updateBy", String.class, "test");
    this.strictUpdateFill(metaObject, "updateTime", Date.class, dateTime);
  }

  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    // 防止全部更新
    interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
    // 多住户
    interceptor.addInnerInterceptor(
        new TenantLineInnerInterceptor(
            new TenantLineHandler() {
              @Override
              public Expression getTenantId() {
                return new StringValue("test");
              }

              // 这是 default 方法,默认返回 false 表示所有表都需要拼多租户条件
              @Override
              public boolean ignoreTable(String tableName) {
                return !"sys".equalsIgnoreCase(tableName);
              }
            }));
    // 分页
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
    return interceptor;
  }
}
