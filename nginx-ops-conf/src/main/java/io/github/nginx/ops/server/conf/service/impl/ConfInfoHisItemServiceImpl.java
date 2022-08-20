package io.github.nginx.ops.server.conf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.conf.domain.ConfInfoHisItem;
import io.github.nginx.ops.server.conf.mapper.ConfInfoHisItemMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoHisItemService;
import org.springframework.stereotype.Service;

/**
 * @author 24709
 * @description 针对表【conf_info_his_item(nginx明细配置记录表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Service
public class ConfInfoHisItemServiceImpl extends ServiceImpl<ConfInfoHisItemMapper, ConfInfoHisItem>
    implements ConfInfoHisItemService {}
