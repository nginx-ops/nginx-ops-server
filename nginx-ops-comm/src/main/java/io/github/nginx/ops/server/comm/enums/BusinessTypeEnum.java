package io.github.nginx.ops.server.comm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lihao3
 * @date 2022/8/21
 */
@Getter
@AllArgsConstructor
public enum BusinessTypeEnum {

  /** 其他 */
  OTHER("other"),
  /** 查询 */
  SELECT("select"),
  /** 新增 */
  INSERT("insert"),
  /** 修改 */
  UPDATE("update"),
  /** 删除 */
  DELETE("delete"),
  /** 上传 */
  UPLOAD("upload"),
  /** 下载 */
  DOWNLOAD("download"),
  /** 授权 */
  GRANT("grant"),
  /** 导出 */
  EXPORT("export"),
  /** 导入 */
  IMPORT("import"),
  /** 强退 */
  FORCE("force"),
  /** 登录 */
  LOGIN("login"),
  /** 路由菜单 */
  Router("router");

  private final String code;
}
