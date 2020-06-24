package com.demon.springbootapi.security;

import com.demon.springbootapi.util.ConstantUtil;
import com.demon.springbootapi.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: RbacPermission
 * @Description: RBAC数据模型控制权限(自定义实现 URL 权限控制)
 * @Author: Demon
 * @Date: 2020/6/4 10:30
 */
@Component("myRbacPermission")
public class MyRbacPermission {

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        System.out.println("进入 RbacPermission···");
        System.out.println("请求URL：" + request.getRequestURI());
        System.out.println("进入authentication（authenticated） 权限处理 ");
        System.out.println("进入权限的权限："+authentication.getName());
        String tokenStr = request.getHeader(ConstantUtil.TOKEN);
        System.out.println("token："+tokenStr);
        try {
            // 判断token
            Claims params = JwtTokenUtil.verifyToken(tokenStr);
            request.setAttribute(ConstantUtil.APP_ID,params.get(ConstantUtil.APP_ID));
            request.setAttribute(ConstantUtil.APP_KEY,params.get(ConstantUtil.APP_KEY));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
