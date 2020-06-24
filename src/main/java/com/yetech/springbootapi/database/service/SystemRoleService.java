package com.yetech.springbootapi.database.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yetech.springbootapi.database.entity.SystemRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
public interface SystemRoleService extends IService<SystemRole> {

    /**
     * 根据用户id获取用户角色
     *
     * @param appId appId
     * @return 返回
     */
    List<SystemRole> getUserRoleByUserId(String appId);
}
