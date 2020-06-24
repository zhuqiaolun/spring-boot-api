package com.demon.springbootapi.database.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demon.springbootapi.database.entity.SystemUser;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
public interface SystemUserService extends IService<SystemUser> {

    /**
     * 根据用户名查找用户
     *
     * @param appId appId
     * @return 返回
     */
    Map<String, Object> getUserByAppId(String appId);

    /**
     * 根据用户名查找用户基本信息
     *
     * @param appId appId
     * @return 返回
     */
    Map<String, Object> getBaseUserInfo(String appId);

    /**
     * 根据用户名查找用户详情信息
     *
     * @param appId appId
     * @return 返回
     */
    Map<String, Object> getQueryUserInfo(String appId);
}
