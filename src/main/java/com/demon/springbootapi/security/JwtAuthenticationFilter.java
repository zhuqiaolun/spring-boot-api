package com.demon.springbootapi.security;

import com.alibaba.fastjson.JSONObject;
import com.demon.springbootapi.util.ConstantUtil;
import com.demon.springbootapi.util.JwtTokenUtil;
import com.demon.springbootapi.util.ResponseBean;
import com.demon.springbootapi.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: JWTAuthenticationFilter
 * @Description: jwt 验证
 * @Author: Demon
 * @Date: 2020/6/3 18:32
 *
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法 ,
 * attemptAuthentication：接收并解析用户凭证。
 * successfulAuthentication：用户成功登录后，这个方法会被调用，我们在这个方法里生成token并返回。
 */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/getToken");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        // 获取到登录的信息
        String appId = request.getParameter(ConstantUtil.APP_ID);
        String appKey = request.getParameter(ConstantUtil.APP_KEY);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appId, appKey));
    }

    /**
     * 成功验证后调用的方法,如果验证成功，就生成参数并返回
     *
     * @param request    request
     * @param response   response
     * @param chain      chain
     * @param authResult authResult
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) {
        System.out.println("进入 JwtAuthenticationFilter···");
        UserDetailEntity userDetailEntity = (UserDetailEntity) authResult.getPrincipal();
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put(ConstantUtil.APP_ID, userDetailEntity.getUsername());
        claims.put(ConstantUtil.APP_KEY, userDetailEntity.getPassword());
        Date expiresAt = DateUtils.addSeconds(new Date(), 7200);
        String token = JwtTokenUtil.createToken(claims, expiresAt);
        ResponseBean<Object> objectResponseBean = new ResponseBean<>();
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put(ConstantUtil.ACCESS_TOKEN, token);
        jsonObject.put(ConstantUtil.EXPIRES_TIME, 7200);
        objectResponseBean.setSuccess(true).setData(jsonObject);
        ResponseUtils.out(response, objectResponseBean);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        failed.printStackTrace();
        response.setStatus(HttpServletResponse.SC_OK);
        ResponseUtils.getErrResponse(response, 500, "appId 或者 appKey 错误");
    }

}
