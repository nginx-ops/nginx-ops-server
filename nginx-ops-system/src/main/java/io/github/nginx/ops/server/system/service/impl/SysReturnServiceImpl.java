package io.github.nginx.ops.server.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.comm.constant.CacheConstants;
import io.github.nginx.ops.server.comm.constant.RequestHeaderConstants;
import io.github.nginx.ops.server.comm.util.json.JsonUtils;
import io.github.nginx.ops.server.system.domain.SysReturn;
import io.github.nginx.ops.server.system.domain.vo.SysReturnVO;
import io.github.nginx.ops.server.system.mapper.SysReturnMapper;
import io.github.nginx.ops.server.system.service.SysReturnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 24709
 * @description 针对表【sys_return】的数据库操作Service实现
 * @createDate 2022-09-11 14:16:01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysReturnServiceImpl extends ServiceImpl<SysReturnMapper, SysReturn>
    implements SysReturnService {

  private final RedisTemplate redisTemplate;

  @Override
  public String getMessage(String code) {
    return this.getMessage(code, null);
  }

  @Override
  public String getMessage(String code, Object... params) {
    SysReturnVO sysReturnVO = getOneByCode(code);
    // 获取流
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    // 获取请求头
    String requestHeader = request.getHeader(RequestHeaderConstants.ACCEPT_LANGUAGE);
    // 缺省为中文
    if (ObjectUtil.isEmpty(requestHeader)) {
      return StrUtil.format(sysReturnVO.getMessage(), params);
    } else if (requestHeader.equalsIgnoreCase(RequestHeaderConstants.EN_US)) {
      return StrUtil.format(sysReturnVO.getEnMessage(), params);
    }
    return StrUtil.format(sysReturnVO.getMessage(), params);
  }

  @Override
  public SysReturnVO getOneByCode(String code) {
    String cacheKey = CacheConstants.APP_NAME + CacheConstants.SEPARATOR + CacheConstants.RETURN;
    if (Boolean.FALSE.equals(redisTemplate.opsForHash().hasKey(cacheKey, code))) {
      Map<String, String> returnMap = this.SysReturnListToMap(this.list());
      // 放入缓存
      redisTemplate.opsForHash().putAll(cacheKey, returnMap);
      log.debug("MAP:{},KEY:{}", JsonUtils.toJSONString(returnMap), code);
      return JSON.parseObject(returnMap.get(code), SysReturnVO.class);
    }
    return JSON.parseObject(
        String.valueOf(redisTemplate.opsForHash().get(cacheKey, code)), SysReturnVO.class);
  }

  @Override
  public Map<String, String> SysReturnListToMap(List<SysReturn> sysReturnList) {
    if (ObjectUtil.isEmpty(sysReturnList)) {
      return Collections.emptyMap();
    }
    HashMap<String, String> returnMap = new HashMap<>(sysReturnList.size());
    sysReturnList.forEach(
        item ->
            returnMap.put(
                item.getCode(),
                JSON.toJSONString(
                    SysReturnVO.builder()
                        .enMessage(item.getEnMessage())
                        .message(item.getMessage())
                        .build())));
    return returnMap;
  }
}
