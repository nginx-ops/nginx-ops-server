package io.github.nginx.ops.server.conf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.nginx.ops.server.conf.domain.ConfInfoUpstreamItem;

/**
 * @author 24709
 * @description 针对表【conf_info_upstream_item(nginx 负载均衡明细配置表)】的数据库操作Mapper
 * @createDate 2022-08-20 14:35:15 @Entity
 *     io.github.nginx.ops.server.conf.domain.ConfInfoUpstreamItem
 */
public interface ConfInfoUpstreamItemMapper extends BaseMapper<ConfInfoUpstreamItem> {}
