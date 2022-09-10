package io.github.nginx.ops.server.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.wf.captcha.SpecCaptcha;
import io.github.nginx.ops.server.admin.domain.dto.LoginDTO;
import io.github.nginx.ops.server.admin.domain.vo.CaptchaVO;
import io.github.nginx.ops.server.admin.service.AdminServer;
import io.github.nginx.ops.server.comm.constant.CacheConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author lihao3
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServerImpl implements AdminServer {

  private static final Snowflake SNOWFLAKE = IdUtil.getSnowflake();
  private final RedisTemplate redisTemplate;

  @Override
  public String login(LoginDTO dto) {
    return null;
  }

  @Override
  public void logout() {
    StpUtil.logout();
  }

  @Override
  public CaptchaVO captcha() {
    String id = SNOWFLAKE.nextIdStr();
    String cacheKey =
        CacheConstants.APP_NAME
            + CacheConstants.SEPARATOR
            + CacheConstants.CAPTCHA
            + CacheConstants.SEPARATOR
            + id;
    SpecCaptcha specCaptcha = new SpecCaptcha();
    String verCode = specCaptcha.text().toLowerCase();
    // 存入redis并设置过期时间为10分钟
    redisTemplate.opsForValue().set(cacheKey, verCode, 10, TimeUnit.MINUTES);
    // 将key和base64返回给前端
    return CaptchaVO.builder().id(id).image(specCaptcha.toBase64()).build();
  }
}
