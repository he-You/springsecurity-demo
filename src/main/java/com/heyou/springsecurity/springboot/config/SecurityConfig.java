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
    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("wilder").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("nick").password("456").authorities("p2").build());
        System.out.println(manager.toString());
        return manager;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //必须有p1权限的用户才能访问资源
                .antMatchers("/r/r1").hasAuthority("p1")
                //必须有p2权限的用户才能访问资源
                .antMatchers("/r/r2").hasAuthority("p2")
                //所有r/**的请求必须认证通过
                .antMatchers("/r/**").authenticated()
                //除了/r/**的请求,其他的请求都可以访问
                .anyRequest().permitAll()
                .and()
                //允许表单登录
                .formLogin()
                //自定义登录成功的页面地址
                .successForwardUrl("/login-sucess");
    }
}
