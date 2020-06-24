package com.demon.springbootapi.database.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demon.springbootapi.database.entity.SystemMenu;
import com.demon.springbootapi.database.entity.SystemRole;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
public interface SystemMenuService extends IService<SystemMenu> {

    /**
     * 根据 角色 获取菜单
     *
     * @param roles roles
     * @return 返回
     */
    List<SystemMenu> getRoleMenuByRoles(List<SystemRole> roles);
}
