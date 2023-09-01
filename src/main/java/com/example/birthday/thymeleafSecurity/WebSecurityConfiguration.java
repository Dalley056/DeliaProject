package com.example.birthday.thymeleafSecurity;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() //<.>
                        .requestMatchers("/svg/*").permitAll() //<.>
                        .anyRequest().authenticated())//<.>
                .formLogin()
                .loginPage("/login") //<.>
                .permitAll()
                .and()
                .logout().permitAll();

        return http.build();
    }
}