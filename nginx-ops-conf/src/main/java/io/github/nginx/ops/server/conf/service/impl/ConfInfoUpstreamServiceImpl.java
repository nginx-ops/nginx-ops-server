package io.github.nginx.ops.server.conf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.conf.domain.ConfInfoUpstream;
import io.github.nginx.ops.server.conf.mapper.ConfInfoUpstreamMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoUpstreamService;
import org.springframework.stereotype.Service;

/**
 * @author 24709
 * @description 针对表【conf_info_upstream(nginx 负载均衡头表配置表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Service
public class ConfInfoUpstreamServiceImpl
    extends ServiceImpl<ConfInfoUpstreamMapper, ConfInfoUpstream>
    implements ConfInfoUpstreamService {}
