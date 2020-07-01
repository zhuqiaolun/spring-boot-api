package com.demon.springbootapi.database.dao;

import com.demon.springbootapi.database.entity.SystemSwaggerUrl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * swagger 的URL 配置 Mapper 接口
 * </p>
 *
 * @author demon
 * @since 2020-07-01
 */
@Mapper
public interface SystemSwaggerUrlMapper extends BaseMapper<SystemSwaggerUrl> {

}
