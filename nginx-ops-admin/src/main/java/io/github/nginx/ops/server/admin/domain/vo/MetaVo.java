package io.github.nginx.ops.server.admin.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 路由显示信息
 *
 * @author wgy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MetaVo {
  /** 设置该路由在侧边栏和面包屑中展示的名字 */
  private String title;

  /** 设置该路由的图标，对应路径src/assets/icons/svg */
  private String icon;

  /** 设置为true，则不会被 <keep-alive>缓存 */
  private boolean noCache;

  /** 内链地址（http(s)://开头） */
  private String link;
}
