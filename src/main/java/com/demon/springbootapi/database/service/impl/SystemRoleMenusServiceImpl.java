package com.demon.springbootapi.database.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demon.springbootapi.database.dao.SystemRoleMenusMapper;
import com.demon.springbootapi.database.entity.SystemRoleMenus;
import com.demon.springbootapi.database.service.SystemRoleMenusService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限菜单表 服务实现类
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
@Service
public class SystemRoleMenusServiceImpl extends ServiceImpl<SystemRoleMenusMapper, SystemRoleMenus> implements SystemRoleMenusService {

}
