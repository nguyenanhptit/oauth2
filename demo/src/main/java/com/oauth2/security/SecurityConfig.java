//package com.oauth2.security;
//
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@AllArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/api/v1/google-link").permitAll()
//                .antMatchers("/api/v1/login").permitAll()
//                .antMatchers("/login-google").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(new RequestAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//}
