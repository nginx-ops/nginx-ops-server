package io.github.nginx.ops.server.conf.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.system.SystemUtil;

/**
 * @author lihao3
 * @date 2022/8/17
 */
public class NginxConfUtils {

  /** 空格 */
  public static final String SPACE = " ";
  /** 项目路径 */
  public static final String CURRENT_DIR = SystemUtil.getUserInfo().getCurrentDir();
  /** 分隔符 */
  public static final String FILE_SEPARATOR = FileUtil.FILE_SEPARATOR;
  /** nginx 配置文件备份路径 */
  public static final String BACK_NGINX_CONF_PATH =
      CURRENT_DIR + "back" + FILE_SEPARATOR + "conf.d" + FILE_SEPARATOR;
  /** nginx 配置文件临时路径 */
  public static final String TMP_NGINX_CONF_PATH =
      CURRENT_DIR + "tmp" + FILE_SEPARATOR + "conf.d" + FILE_SEPARATOR;
  /** nginx index配置文件临时路径 */
  public static final String TMP_NGINX_INDEX_CONF_PATH =
      CURRENT_DIR + "tmp" + FILE_SEPARATOR + "conf.d" + FILE_SEPARATOR + "nginx.conf";

  /**
   * 创建临时文件夹
   *
   * @param serverConf 文件内容
   * @param fileName 文件名称
   * @return 文件全路径
   */
  public static String createTempConfFile(String serverConf, String fileName) {
    String filePath = TMP_NGINX_CONF_PATH + fileName + ".conf";
    FileUtil.writeUtf8String(serverConf, filePath);
    return filePath;
  }
}
