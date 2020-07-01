package com.demon.springbootapi.database.service.impl;

import com.demon.springbootapi.database.entity.SystemSwaggerUrl;
import com.demon.springbootapi.database.dao.SystemSwaggerUrlMapper;
import com.demon.springbootapi.database.service.SystemSwaggerUrlService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * swagger 的URL 配置 服务实现类
 * </p>
 *
 * @author demon
 * @since 2020-07-01
 */
@Service
public class SystemSwaggerUrlServiceImpl extends ServiceImpl<SystemSwaggerUrlMapper, SystemSwaggerUrl> implements SystemSwaggerUrlService {

}
