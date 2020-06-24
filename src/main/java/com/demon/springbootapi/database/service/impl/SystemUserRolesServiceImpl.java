package com.demon.springbootapi.database.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demon.springbootapi.database.dao.SystemUserRolesMapper;
import com.demon.springbootapi.database.entity.SystemUserRoles;
import com.demon.springbootapi.database.service.SystemUserRolesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户权限表 服务实现类
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
@Service
public class SystemUserRolesServiceImpl extends ServiceImpl<SystemUserRolesMapper, SystemUserRoles> implements SystemUserRolesService {

}
