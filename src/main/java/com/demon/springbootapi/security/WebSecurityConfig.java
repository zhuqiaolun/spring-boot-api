package com.demon.springbootapi.security;

import com.demon.springbootapi.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: WebSecurityConfig
 * @Description: 自定义 Spring Security 配置
 * @Author: Demon
 * @Date: 2020/6/1 21:40
 *
 * prePostEnabled :决定Spring Security的前注解是否可用 [@PreAuthorize,@PostAuthorize,..]
 * secureEnabled : 决定是否Spring Security的保障注解 [@Secured] 是否可用
 * jsr250Enabled ：决定 JSR-250 annotations 注解[@RolesAllowed..] 是否可用.
 * 开启 Spring Security 方法级安全注解 @EnableGlobalMethodSecurity
 * <p>
 * 保护URL常用的方法有：
 * authenticated() ：保护UrL，需要用户登录
 * permitAll() ：指定URL无需保护，一般应用与静态资源文件
 * hasRole(String role) ：限制单个角色访问，角色将被增加 “ROLE_” .所以”ADMIN” 将和 “ROLE_ADMIN”进行比较. 另一个方法是hasAuthority(String authority)
 * hasAnyRole(String… roles) ：允许多个角色访问. 另一个方法是hasAnyAuthority(String… authorities)
 * 其他有用的方法有：
 * access(String attribute)：该方法使用 SPEL, 所以可以创建复杂的限制.
 * hasIpAddress(String ipaddressExpression)：限制IP地址或子网
 */
@Slf4j
@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userDetailsService")
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

    /**
     * 静态资源设置
     */
    @Override
    public void configure(WebSecurity webSecurity) {
        //不拦截特定的访问路径
        webSecurity.ignoring().antMatchers("/")
                .antMatchers("/test","/test/**");
    }

    /**
     * http请求设置
     */
    @SuppressWarnings({"SpringElInspection"})
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // 开启跨域共享
                .cors()
                .and()
                // 跨域伪造请求限制.无效
                .csrf().disable().authorizeRequests();
        http
                /*权限不足 可以在这里自定义返回内容 */
                .exceptionHandling()
                .authenticationEntryPoint(new MyAuthenticationEntryPoint());
        http
                // 开启授权认证(所有的请求都要验证)
                .authorizeRequests()
                // 不需要授权访问的
                .antMatchers("/auth/**").permitAll()
                // 不需要token授权访问，但限制IP访问(可设置IP范围)
                .antMatchers(
                        "/weather/**")
                .access("hasIpAddress('127.0.0.1') or hasIpAddress('192.168.0.0/24') ")
                // 需要token授权访问的
                .antMatchers(
                        "/user/**")
                .access("@myRbacPermission.hasPermission(request, authentication)")
                // OPTIONS预检请求不处理
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // 其他请求不处理
                .anyRequest().permitAll();
        http
                .formLogin().disable()
                .logout().disable()
                //不创建 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                //配置拦截器
                .addFilter(new JwtAuthenticationFilter(this.authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(this.authenticationManager()))
        ;
    }

    /**
     * 在 MyRbacPermission 返回 false 执行
     */
    class MyAuthenticationEntryPoint  implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) {
            authenticationException.printStackTrace();
            ResponseUtils.getErrResponse(response,40000,"无权访问");
        }
    }



}
