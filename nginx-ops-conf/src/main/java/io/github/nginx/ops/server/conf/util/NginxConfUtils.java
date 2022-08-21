package io.github.nginx.ops.server.conf.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;
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
  /** 获取项目路径 */
  public static final String FILE_SEPARATOR = FileUtil.FILE_SEPARATOR;
  /** nginx 配置文件临时路径 */
  public static final String BACK_NGINX_CONF_PATH =
      CURRENT_DIR + "back" + FILE_SEPARATOR + "conf.d" + FILE_SEPARATOR;
  /** nginx 配置文件临时路径 */
  public static final String TMP_NGINX_CONF_PATH =
      CURRENT_DIR + "tmp" + FILE_SEPARATOR + "conf.d" + FILE_SEPARATOR;
  /** nginx 配置文件临时路径 */
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

  /**
   * nginx 运行状态
   *
   * @return
   */
  public static Boolean status() {
    boolean status;
    if (FileUtil.isWindows()) {
      String[] command = {"tasklist"};
      String rs = RuntimeUtil.execForStr(command);
      status = rs.toLowerCase().contains("nginx.exe");
    } else {
      String[] command = {"/bin/sh", "-c", "ps -ef | grep nginx"};
      String rs = RuntimeUtil.execForStr(command);
      status = rs.contains("nginx: master process") || rs.contains("nginx: worker process");
    }
    return status;
  }

  /**
   * 测试nginx配置文件
   *
   * @return 命令返回语句
   */
  public static String test(String nginxExe) {
    String result =
        RuntimeUtil.execForStr(
            nginxExe
                + NginxConfUtils.SPACE
                + "-t -c"
                + NginxConfUtils.SPACE
                + NginxConfUtils.TMP_NGINX_INDEX_CONF_PATH);
    return result;
  }

  /**
   * 重新装配nginx配置文件
   *
   * @param nginxReloadCmd
   * @return
   */
  public static String reload(String nginxReloadCmd) {
    return RuntimeUtil.execForStr(nginxReloadCmd);
  }
}
