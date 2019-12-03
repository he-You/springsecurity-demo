package com.heyou.springsecurity.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.annotation.Resource;

/**
 * 简要说明:
 *
 * @author heyou
 * @date 2019-12-02 23:07
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    MyUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 以下不用登录就能访问
                .antMatchers("/","errorPage").permitAll()
                // 其他请求必须登录才能访问
                .antMatchers("r/r1").hasAuthority("ROLE_USER")
                .antMatchers("r/r2").hasAuthority("p2")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successForwardUrl("/login-sucess");
        // 开启注销功能
        http.logout();

    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
