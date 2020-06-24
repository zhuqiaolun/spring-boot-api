package com.demon.springbootapi;

import com.demon.springbootapi.database.entity.SystemMenu;
import com.demon.springbootapi.database.entity.SystemRole;
import com.demon.springbootapi.database.service.SystemMenuService;
import com.demon.springbootapi.database.service.SystemRoleService;
import com.demon.springbootapi.database.service.SystemUserService;
import com.demon.springbootapi.dto.UserDto;
import com.demon.springbootapi.util.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class SpringBootApiApplicationTests {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private SystemRoleService systemRoleService;
    @Resource
    private SystemMenuService systemMenuService;

    @Test
    void testHttps(){
        String url = "https://www.baidu.com/";
        String resp = restTemplate.getForObject(url, String.class);
        System.out.println(resp);
    }

    @Test
    void contextLoads() {
        Map<String, Object> map = systemUserService.getUserByAppId("1000000000000000");
        UserDto userDto = EntityUtil.mapToEntity(map, UserDto.class);
        System.out.println(userDto);
        List<SystemRole> systemRoles = systemRoleService.getUserRoleByUserId(userDto.getAppId());
        System.out.println(systemRoles);
        List<SystemMenu> systemMenus = systemMenuService.getRoleMenuByRoles(systemRoles);
        System.out.println(systemMenus);
    }

    @Test
     void getPassword(){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encode = passwordEncoder.encode("1234567890");
        log.info("加密后的密码:" + encode);
        log.info("bcrypt密码对比:" + passwordEncoder.matches("1234567890", encode));
    }

}
