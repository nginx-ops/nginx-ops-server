package io.github.nginx.ops.server.conf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.conf.domain.ConfInfo;
import io.github.nginx.ops.server.conf.mapper.ConfInfoMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoService;
import org.springframework.stereotype.Service;

/**
 * @author 24709
 * @description 针对表【conf_info(nginx配置表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Service
public class ConfInfoServiceImpl extends ServiceImpl<ConfInfoMapper, ConfInfo>
    implements ConfInfoService {}
