package com.yetech.springbootapi.database.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yetech.springbootapi.database.dao.SystemRoleMapper;
import com.yetech.springbootapi.database.entity.SystemRole;
import com.yetech.springbootapi.database.entity.SystemUserRoles;
import com.yetech.springbootapi.database.service.SystemRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements SystemRoleService {

    @Resource
    private SystemUserRolesServiceImpl systemUserRolesService;

    @Override
    public List<SystemRole> getUserRoleByUserId(String appId) {
        QueryWrapper<SystemUserRoles> systemUserRolesQueryWrapper = new QueryWrapper<>();
        systemUserRolesQueryWrapper.setEntity(new SystemUserRoles().setUserId(appId));
        List<SystemUserRoles> systemUserRoles = systemUserRolesService.list(systemUserRolesQueryWrapper);
        if (systemUserRoles != null) {
            List<Integer> userRoleList = new ArrayList<>();
            for (SystemUserRoles userRoles : systemUserRoles) {
                userRoleList.add(userRoles.getRoleId());
            }
            QueryWrapper<SystemRole> systemRoleQueryWrapper = new QueryWrapper<>();
            systemRoleQueryWrapper.in("r_id", userRoleList);
            return this.list(systemRoleQueryWrapper);
        }
        return null;
    }
}
