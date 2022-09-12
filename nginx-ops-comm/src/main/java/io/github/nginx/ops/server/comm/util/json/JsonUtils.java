package io.github.nginx.ops.server.comm.util.json;

import com.alibaba.fastjson2.JSON;

/**
 * json 工具类
 *
 * @author lihao3
 */
public class JsonUtils {

  /**
   * 将对象转成json格式
   *
   * @param object
   * @return String
   */
  public static String toJSONString(Object object) {
    return JSON.toJSONString(object);
  }
}
