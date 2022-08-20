package io.github.nginx.ops.server.conf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.nginx.ops.server.conf.domain.ConfInfoTemplate;
import io.github.nginx.ops.server.conf.mapper.ConfInfoTemplateMapper;
import io.github.nginx.ops.server.conf.service.ConfInfoTemplateService;
import org.springframework.stereotype.Service;

/**
 * @author 24709
 * @description 针对表【conf_info_template(nginx配置模板表)】的数据库操作Service实现
 * @createDate 2022-08-20 14:35:15
 */
@Service
public class ConfInfoTemplateServiceImpl
    extends ServiceImpl<ConfInfoTemplateMapper, ConfInfoTemplate>
    implements ConfInfoTemplateService {}
