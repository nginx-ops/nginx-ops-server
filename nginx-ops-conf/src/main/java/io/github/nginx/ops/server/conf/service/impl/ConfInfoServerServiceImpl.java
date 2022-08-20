package io.github.nginx.ops.server.conf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.conf.domain.ConfInfoServer;
import io.github.nginx.ops.server.conf.mapper.ConfInfoServerMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoServerService;
import org.springframework.stereotype.Service;

/**
 * @author 24709
 * @description 针对表【conf_info_server(nginx服务配置表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Service
public class ConfInfoServerServiceImpl extends ServiceImpl<ConfInfoServerMapper, ConfInfoServer>
    implements ConfInfoServerService {}
