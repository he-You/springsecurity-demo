package com.heyou.springsecurity.springboot.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 简要说明:
 *
 * @author heyou
 * @date 2019-12-03 22:24
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 加密后的密码 123
        String str = "$2a$10$OhuRAM2ymA9PQwMvVSp28eWtsXTz4rhU9xLKhWOzXzcq3GZjcubEy";

        // 添加权限
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        User user = new User(s,str,authorities);


        return user;
    }
}
