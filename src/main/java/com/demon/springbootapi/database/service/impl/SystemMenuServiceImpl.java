package com.demon.springbootapi.database.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demon.springbootapi.database.dao.SystemMenuMapper;
import com.demon.springbootapi.database.entity.SystemMenu;
import com.demon.springbootapi.database.entity.SystemRole;
import com.demon.springbootapi.database.entity.SystemRoleMenus;
import com.demon.springbootapi.database.service.SystemMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements SystemMenuService {

    @Resource
    private SystemRoleMenusServiceImpl systemRoleMenusService;

    @Override
    public List<SystemMenu> getRoleMenuByRoles(List<SystemRole> roles) {
        if (roles != null) {
            List<Integer> roleList = new ArrayList<>();
            for (SystemRole systemRole : roles) {
                roleList.add(systemRole.getRId());
            }
            QueryWrapper<SystemRoleMenus> systemRoleMenusQueryWrapper = new QueryWrapper<>();
            systemRoleMenusQueryWrapper.in("role_id", roleList);
            List<SystemRoleMenus> systemRoleMenusList = systemRoleMenusService.list(systemRoleMenusQueryWrapper);
            if (systemRoleMenusList != null) {
                List<Integer> systemMenuList = new ArrayList<>();
                for (SystemRoleMenus systemRoleMenus : systemRoleMenusList) {
                    systemMenuList.add(systemRoleMenus.getMenuId());
                }
                QueryWrapper<SystemMenu> systemMenuQueryWrapper = new QueryWrapper<>();
                systemMenuQueryWrapper.in("m_id", systemMenuList);
                return this.list(systemMenuQueryWrapper);
            }
        }
        return null;
    }
}
