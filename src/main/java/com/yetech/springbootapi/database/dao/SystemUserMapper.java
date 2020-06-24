package com.yetech.springbootapi.database.dao;

import com.yetech.springbootapi.database.entity.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {

}
