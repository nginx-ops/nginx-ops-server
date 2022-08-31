package io.github.nginx.ops.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lihao3
 * @date 2022/8/20
 */
@RefreshScope
@MapperScan("io.github.nginx.ops.server.*.mapper")
@EnableAsync
@EnableScheduling
@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
public class NginxOpsServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(NginxOpsServerApplication.class, args);
  }
}
