package com.yetech.springbootapi.database.dao;

import com.yetech.springbootapi.database.entity.SystemRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
@Mapper
public interface SystemRoleMapper extends BaseMapper<SystemRole> {

}
