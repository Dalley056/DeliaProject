package com.example.birthday;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity(
        debug = true
)
public class SecurityConfig {

   @Bean
   public PasswordEncoder passwordEncoder() {
      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
   }


   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
              .securityMatcher("/**")
              .authorizeHttpRequests(auth -> {
                 auth
                         .dispatcherTypeMatchers(
                                 DispatcherType.FORWARD, DispatcherType.ERROR
                         ).permitAll()
                         .requestMatchers("/error", "/errors/**").permitAll()
                         .requestMatchers("/**").permitAll();


              })
              .formLogin(formLogin ->
                      formLogin
                              .loginPage("/login").permitAll()
                              .loginProcessingUrl("/login").permitAll()
                              .defaultSuccessUrl("/", true)
                              .failureUrl("/login?error=true")
              ).logout(logout ->
                      logout
                              .logoutUrl("/logout").permitAll()
                              .logoutSuccessUrl("/").permitAll()
                              .invalidateHttpSession(true)
                              .deleteCookies("JSESSIONID"));
      return http.build();
   }

   @Controller
   class LoginController {
      @RequestMapping("/login")
      String login() {
         return "login";
      }

      @GetMapping("/logout")
       public String logout() {
          return "logout";
      }

   }}

//   @Bean
//   public InMemoryUserDetailsManager userDetailsService(){
//      UserDetails user = User.builder()
//              .username("user")
//              .password(passwordEncoder().encode("verysecure"))
//              .roles("USER")
//              .build();
//      UserDetails admin = User.builder()
//              .username("admin")
//              .password(passwordEncoder().encode("evenmoresecure"))
//              .roles("ADMIN")
//              .build();
//      return new InMemoryUserDetailsManager(user,admin);
//   }
//@Bean
//public ActiveUserStore activeUserStore(){
//   return new ActiveUserStore();
//}
//}
