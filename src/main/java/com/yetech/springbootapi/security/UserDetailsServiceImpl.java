package com.yetech.springbootapi.security;

import com.yetech.springbootapi.database.entity.SystemUser;
import com.yetech.springbootapi.database.service.SystemUserService;
import com.yetech.springbootapi.dto.UserDto;
import com.yetech.springbootapi.util.EntityUtil;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName: UserDetailServiceImpl
 * @Description:  自定义实现 UserDetailsService 接口，获取用户相关信息
 * @Author: Demon
 * @Date: 2020/6/1 21:46
 * @Copyright: 2020 www.yetech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于元镁信息科技（上海）有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service(value = "userDetailsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SystemUserService systemUserService;

    @Override
    public UserDetailEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查找用户
        Map<String, Object> map = systemUserService.getUserByAppId(username);
        UserDto userDto = EntityUtil.mapToEntity(map, UserDto.class);
        System.out.println(userDto);
        if (userDto != null) {
            System.out.println("UserDetailsService");
            return new UserDetailEntity(username,userDto.getAppKey());
        } else {
            System.out.println(username +" not found");
            throw new UsernameNotFoundException(username +" not found");
        }
    }

}
