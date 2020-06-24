package com.demon.springbootapi.database.dao;

import com.demon.springbootapi.database.entity.SystemUserRoles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户权限表 Mapper 接口
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
@Mapper
public interface SystemUserRolesMapper extends BaseMapper<SystemUserRoles> {

}
