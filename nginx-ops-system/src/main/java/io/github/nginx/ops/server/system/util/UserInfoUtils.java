package io.github.nginx.ops.server.system.util;

import cn.dev33.satoken.stp.StpUtil;
import io.github.nginx.ops.server.comm.constant.CacheConstants;
import io.github.nginx.ops.server.comm.util.json.JsonUtils;
import io.github.nginx.ops.server.system.domain.dto.UserInfo;

/**
 * @author lihao3
 * @date 2022/9/16 12:08
 */
public class UserInfoUtils {

  public static UserInfo getUserInfo() {
    String jsonText = StpUtil.getSession().getString(CacheConstants.USERINFO);
    return JsonUtils.parseObject(jsonText, UserInfo.class);
  }
}
