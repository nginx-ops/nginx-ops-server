package io.github.nginx.ops.server.conf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.nginx.ops.server.conf.domain.ConfInfoUpstream;

/**
 * @author 24709
 * @description 针对表【conf_info_upstream(nginx 负载均衡头表配置表)】的数据库操作Mapper
 * @createDate 2022-08-20 14:35:15 @Entity io.github.nginx.ops.server.conf.domain.ConfInfoUpstream
 */
public interface ConfInfoUpstreamMapper extends BaseMapper<ConfInfoUpstream> {}
