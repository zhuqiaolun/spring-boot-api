package com.demon.springbootapi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @ClassName: UserEntity
 * @Description: 自定义实现 UserDetails 接口，扩展属性
 * @Author: Demon
 * @Date: 2020/6/1 21:53
 */
public class UserDetailEntity  implements UserDetails {

    private static final long serialVersionUID = -9005214545793249372L;

    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    UserDetailEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
