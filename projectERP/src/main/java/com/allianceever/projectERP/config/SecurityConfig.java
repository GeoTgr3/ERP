package com.allianceever.projectERP.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**AUTHORIZATION*/
    @SuppressWarnings("removal")

    @Bean /**AUTHORIZATION*/
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/holidays.html").permitAll()
                .requestMatchers("/leaves.html").authenticated()
                .anyRequest().permitAll()
                .and().formLogin()
                .and().build();
    }





    @Bean
    public UserDetailsService userDetailsService(){
        return new UserInfoUserDetailsService();
    }

}