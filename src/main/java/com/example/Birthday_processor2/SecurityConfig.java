package com.example.Birthday_processor2;


import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity(
        debug = true
)
public class SecurityConfig {
   private final PasswordEncoder passwordEncoder;
   public SecurityConfig(PasswordEncoder passwordEncoder){
      this.passwordEncoder=passwordEncoder;
   }

   @Bean
   public InMemoryUserDetailsManager userDetailsService() {
      UserDetails user= User.builder()
              .username("user")
              .password(passwordEncoder
                      .encode("verysecure"))
              .roles("USER").build();
//      UserDetails admin= User.builder()
//              .username("admin")
//              .password(passwordEncoder.encode("evenmoresecure")
////                      .roles("USER", "ADMIN")
//                      .build());
      return new InMemoryUserDetailsManager(user);
   }

//   @Bean
//   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//      http.authorizeHttpRequests(authz -> authz.requestMatchers
//              ("users/create").hasRole("ADMIN")
//                      .requestMatchers
//                              ("/users/*/delete").hasRole("ADMIN")
//                      .requestMatchers
//                              (HttpMethod.GET, "/users/*").hasRole("USER")
//                                      .requestMatchers
//                                              (HttpMethod.POST, "/users/*").hasRole("ADMIN")
//                                      .anyRequest()
//                                      .authentificated())
//                      .formLogin().permitAll()
//                      .and()
//                      .logout().permitAll();
//      return http.build();
//   }

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
      http
              .securityMatcher("/**")
              .authorizeHttpRequests(auth -> {
                 auth
                         .dispatcherTypeMatchers(
                                 DispatcherType.FORWARD, DispatcherType.ERROR
                         ).permitAll()
                         .requestMatchers("/error.css", "/errors/**").permitAll()
                         .requestMatchers("/**").permitAll();


              })
              .formLogin(formLogin ->
                      formLogin
                              .loginPage("/login").permitAll()
                              .loginProcessingUrl("/login").permitAll()
                              .defaultSuccessUrl("/",true)
                              .failureUrl("/login?error.css=true")
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
      @RequestMapping("/logout")
      String logout() {
         return "logout";
      }
   }
}

