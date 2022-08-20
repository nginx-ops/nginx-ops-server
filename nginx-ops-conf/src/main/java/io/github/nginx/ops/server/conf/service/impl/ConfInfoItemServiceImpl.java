package io.github.nginx.ops.server.conf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.conf.domain.ConfInfoItem;
import io.github.nginx.ops.server.conf.mapper.ConfInfoItemMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoItemService;
import org.springframework.stereotype.Service;

/**
 * @author 24709
 * @description 针对表【conf_info_item(nginx明细配置表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Service
public class ConfInfoItemServiceImpl extends ServiceImpl<ConfInfoItemMapper, ConfInfoItem>
    implements ConfInfoItemService {}
