package com.yetech.springbootapi.database.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yetech.springbootapi.database.dao.SystemUserMapper;
import com.yetech.springbootapi.database.entity.SystemUser;
import com.yetech.springbootapi.database.service.SystemUserService;
import com.yetech.springbootapi.dto.UserDto;
import com.yetech.springbootapi.util.CacheConst;
import com.yetech.springbootapi.util.EntityUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
@Service
@CacheConfig(cacheNames = CacheConst.USER_INFO_SERVICE_CACHE)
@Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

    @Cacheable
    @Override
    public Map<String, Object> getUserByAppId(String appId) {
        QueryWrapper<SystemUser> systemUserQueryWrapper = new QueryWrapper<>();
        systemUserQueryWrapper.select("app_id","app_key");
        systemUserQueryWrapper.setEntity(new SystemUser().setAppId(appId));
        SystemUser systemUser = this.baseMapper.selectOne(systemUserQueryWrapper);
        UserDto  userDto = new UserDto();
        userDto.setAppId(systemUser.getAppId()).setAppKey(systemUser.getAppKey());
        return EntityUtil.entityToMap(userDto);
    }

    @Cacheable
    @Override
    public Map<String, Object> getBaseUserInfo(String appId) {
        System.out.println("执行 getBaseUserInfo 方法·····");
        QueryWrapper<SystemUser> systemUserQueryWrapper = new QueryWrapper<>();
        systemUserQueryWrapper.select("app_id","user_name");
        systemUserQueryWrapper.setEntity(new SystemUser().setAppId(appId));
        SystemUser systemUser = this.baseMapper.selectOne(systemUserQueryWrapper);
        UserDto  userDto = new UserDto();
        userDto.setAppId(systemUser.getAppId()).setUserName(systemUser.getUserName());
        return EntityUtil.entityToMap(userDto);
    }

    @Cacheable
    @Override
    public Map<String, Object> getQueryUserInfo(String appId) {
        System.out.println("执行 getQueryUserInfo 方法·····");
        QueryWrapper<SystemUser> systemUserQueryWrapper = new QueryWrapper<>();
        systemUserQueryWrapper.select("app_id","nick_name","user_name","create_time");
        systemUserQueryWrapper.setEntity(new SystemUser().setAppId(appId));
        SystemUser systemUser = this.baseMapper.selectOne(systemUserQueryWrapper);
        UserDto  userDto = new UserDto();
        userDto.setAppId(systemUser.getAppId()).setUserName(systemUser.getUserName()).setNickName(systemUser.getNickName());
        return EntityUtil.entityToMap(userDto);
    }


}
