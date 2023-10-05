package com.metrodata.serverapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("$2a$12$xCc1g8C69OkTCNnAfYp8wusPuWtejccHi140BQY9aSBAlZMaMFKfq")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("$2a$12$U4sH1gOmko/6lcM56EdqOeJ.wR0PO/P9sUDLi9PEOl5zIMPNN146W")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/region/**").hasRole("USER")
                .antMatchers("/employee/**").hasRole("ADMIN")
                .antMatchers("/country/**").hasAnyRole("USER","ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
