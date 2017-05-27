package com.ml.config;

import com.ml.security.CustomSecurityInterceptor;
import com.ml.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomSecurityInterceptor customSecurityInterceptor;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // @formatter:off
        web.ignoring()
           .antMatchers(HttpMethod.OPTIONS,"/**")
           .antMatchers("/static/**")
           .antMatchers("/")
           .antMatchers("/WEB-INF/**")
           .antMatchers("/druid/**");
        // @formatter:on
    }

    /**
     * csrf 支持不会处理 "GET", "HEAD", "TRACE", "OPTIONS"请求, 注销登录默认需要post请求
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .addFilterBefore(customSecurityInterceptor, FilterSecurityInterceptor.class)
            .headers()
                .frameOptions()
                .disable()
        .and()
            .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/access").permitAll()
                .anyRequest().authenticated()
        .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
        .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .and()
            .rememberMe()
                .userDetailsService(customUserDetailService)
                .tokenValiditySeconds(86400)
        .and()
            .exceptionHandling()
            .authenticationEntryPoint(http401UnauthorizedEntryPoint())
            .accessDeniedPage("/denied");
        // @formatter:on
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * spring security taglib
     */
    @Bean
    public DefaultWebInvocationPrivilegeEvaluator defaultWebInvocationPrivilegeEvaluator() {
        return new DefaultWebInvocationPrivilegeEvaluator(customSecurityInterceptor);
    }

    @Bean
    public Http401AuthenticationEntryPoint http401UnauthorizedEntryPoint() {
        return new Http401AuthenticationEntryPoint("maling-app");
    }

}