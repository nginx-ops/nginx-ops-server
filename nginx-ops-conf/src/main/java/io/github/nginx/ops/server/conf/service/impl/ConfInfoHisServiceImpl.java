package io.github.nginx.ops.server.conf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.conf.domain.ConfInfoHis;
import io.github.nginx.ops.server.conf.mapper.ConfInfoHisMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoHisService;
import org.springframework.stereotype.Service;

/**
 * @author 24709
 * @description 针对表【conf_info_his(nginx配置历史表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Service
public class ConfInfoHisServiceImpl extends ServiceImpl<ConfInfoHisMapper, ConfInfoHis>
    implements ConfInfoHisService {}
