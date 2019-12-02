package com.heyou.springsecurity.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 简要说明:
 *
 * @author heyou
 * @date 2019-12-02 23:07
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("heyou").password("123456").authorities("p1").build());
        manager.createUser(User.withUsername("nick").password("123456").authorities("p2").build());
        return manager;
    }
    /**
     * 密码编辑器
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    /**
     * 安全拦截机制
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("r/r1").hasAuthority("p1")
                .antMatchers("r/r2").hasAuthority("p2")
                //所有r/**的请求必须认证通过
                .antMatchers("r/**").authenticated()
                //除了/r/**的请求,其他的请求都可以访问
                .anyRequest().authenticated()
                .and()
                //允许表单登录
                .formLogin()
                //自定义登录成功的页面地址
                .successForwardUrl("/login-sucess");
    }
}
