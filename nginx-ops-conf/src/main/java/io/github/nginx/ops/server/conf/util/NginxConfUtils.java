package io.github.nginx.ops.server.conf.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.system.SystemUtil;
import com.github.odiszapc.nginxparser.NgxBlock;
import com.github.odiszapc.nginxparser.NgxParam;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoCommDTO;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoServerDTO;
import io.github.nginx.ops.server.conf.domain.dto.ConfInfoServerItemDTO;

/**
 * @author lihao3
 * @date 2022/8/17
 */
public class NginxConfUtils {

  /** 空格 */
  public static final String SPACE = " ";
  /** 项目路径 */
  public static final String CURRENT_DIR = SystemUtil.getUserInfo().getCurrentDir();
  /** 获取项目路径 */
  public static final String FILE_SEPARATOR = FileUtil.FILE_SEPARATOR;
  /** nginx 配置文件临时路径 */
  public static final String TMP_NGINX_CONF_PATH =
      CURRENT_DIR + "conf.d" + FILE_SEPARATOR + "tmp" + FILE_SEPARATOR + "nginx.conf";
  /** nginx 配置文件测试路径 */
  public static final String TEST_NGINX_CONF_PATH =
      CURRENT_DIR + "conf.d" + FILE_SEPARATOR + "test" + FILE_SEPARATOR + "nginx.conf";
  /** nginx 配置文件生产路径 */
  public static final String PROD_NGINX_CONF_PATH =
      CURRENT_DIR + "conf.d" + FILE_SEPARATOR + "prod" + FILE_SEPARATOR + "nginx.conf";

  /**
   * 构建sever 配置文件
   *
   * @param dto
   * @return
   */
  public static NgxBlock buildBlockServer(ConfInfoServerDTO dto) {
    NgxParam ngxParam = null;
    NgxBlock ngxBlockServer = new NgxBlock();
    ngxBlockServer.addValue("server");
    // 监听域名
    ngxParam = new NgxParam();
    ngxParam.addValue("server_name " + dto.getServerName());
    // 监听端口
    ngxParam = new NgxParam();
    String value = "listen " + dto.getPort();
    if (dto.getIsSsl() != null && dto.getIsSsl()) {
      value += " ssl";
    }
    if (dto.getIsHttp2() != null && dto.getIsHttp2()) {
      value += " http2";
    }
    ngxParam.addValue(value);
    ngxBlockServer.addEntry(ngxParam);
    // https添加80端口重写
    if (Boolean.TRUE.equals(dto.getHttpToHttps())) {
      NgxBlock ngxBlock = new NgxBlock();
      ngxBlock.addValue("if ($scheme = http)");
      ngxParam = new NgxParam();
      ngxParam.addValue("return 301 https://$host:" + dto.getHttpPort() + "$request_uri");
      ngxBlock.addEntry(ngxParam);
      ngxBlockServer.addEntry(ngxBlock);
    }
    // 额外参数
    if (ObjectUtil.isNotEmpty(dto.getConfInfoCommList())) {
      dto.getConfInfoCommList().forEach(item -> setSameParam(item, ngxBlockServer));
    }
    // location参数配置
    if (ObjectUtil.isNotEmpty(dto.getConfInfoServerItemList())) {
      for (ConfInfoServerItemDTO item : dto.getConfInfoServerItemList()) {
        NgxBlock ngxBlockLocation = new NgxBlock();
        ngxBlockLocation.addValue("location");
        ngxBlockLocation.addValue(item.getPath());
        // 设置header
        if (item.getIsHeader()) {
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header Host $host");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header X-Real-IP $remote_addr");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header X-Forwarded-Host $http_host");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header X-Forwarded-Port $server_port");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header X-Forwarded-Proto $scheme");
          ngxBlockLocation.addEntry(ngxParam);
        }
        // 设置Websocket
        if (item.getIsWebsocket()) {
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_http_version 1.1");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header Upgrade $http_upgrade");
          ngxBlockLocation.addEntry(ngxParam);
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_set_header Connection \"upgrade\"");
          ngxBlockLocation.addEntry(ngxParam);
        }
        // redirect http转https
        if (dto.getIsSsl()) {
          ngxParam = new NgxParam();
          ngxParam.addValue("proxy_redirect http:// https://");
          ngxBlockLocation.addEntry(ngxParam);
        }
        ngxBlockServer.addEntry(ngxBlockLocation);
      }
    }
    return ngxBlockServer;
  }

  /**
   * 构建其他配置文件
   *
   * @param dto
   * @param ngxBlock
   */
  public static void setSameParam(ConfInfoCommDTO dto, NgxBlock ngxBlock) {
    NgxParam ngxParam = new NgxParam();
    ngxParam.addValue(dto.getName().trim() + SPACE + dto.getValue().trim());
    ngxBlock.addEntry(ngxParam);
  }

  public static String createTempConfFile(String serverConf, String fileName) {
    String filePath =
        CURRENT_DIR + "tmp" + FILE_SEPARATOR + "conf.d" + FILE_SEPARATOR + fileName + ".conf";
    FileUtil.writeUtf8String(serverConf, filePath);
    return filePath;
  }
}
