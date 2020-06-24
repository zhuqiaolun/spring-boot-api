package com.demon.springbootapi.security;

import com.demon.springbootapi.util.IpUtil;
import com.demon.springbootapi.util.ResponseUtils;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: JwtAuthorizationFilter
 * @Description: jwt 鉴权
 * @Author: Demon
 * @Date: 2020/6/3 18:36
 *
 * 验证成功当然就是进行鉴权了
 * 登录成功之后走此类进行鉴权操作
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain){
        System.out.println("进入 JwtAuthorizationFilter···");
        try {
            /* 打印请求的内容获取请求头中的User-Agent */
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            log.info("请求URL:{}" , request.getRequestURL().toString());
            log.info("请求浏览器:{}", userAgent.getBrowser().toString());
            log.info("请求浏览器版本:{}",userAgent.getBrowserVersion());
            log.info("请求操作系统:{}", userAgent.getOperatingSystem().toString());
            log.info("请求访问IP:{}" , IpUtil.getIpAddr(request));
            log.info("请求类型:{}", request.getMethod());
            //放行
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            //返回验证失败
            ResponseUtils.getErrResponse(response,40000,e.getMessage());
        }
    }

}
