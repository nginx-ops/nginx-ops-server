package io.github.nginx.ops.server.system.enums;

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
  ;

  private final String code;
}
