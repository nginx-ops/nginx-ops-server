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

  /**
   * 将JSON字符串转为实体类
   *
   * @param text
   * @param clazz
   * @param <T>
   * @return
   */
  public static <T> T parseObject(String text, Class<T> clazz) {
    return JSON.parseObject(text, clazz);
  }
}
