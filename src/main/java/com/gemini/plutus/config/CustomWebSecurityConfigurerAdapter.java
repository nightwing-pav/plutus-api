package com.gemini.plutus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter {
  @Autowired private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth
        .inMemoryAuthentication()
        .withUser("admin")
        .password("{noop}admin")
        .authorities("ROLE_USER");
      
      auth
        .inMemoryAuthentication()
        .withUser("luke")
        .password("{noop}newRepublic")
        .authorities("ROLE_USER");

      auth
        .inMemoryAuthentication()
        .withUser("obi-wan")
        .password("{noop}jediMaster")
        .authorities("ROLE_USER");
      
      auth
        .inMemoryAuthentication()
        .withUser("no-user")
        .password("{noop}none")
        .authorities("ROLE_USER");
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.authorizeHttpRequests()
              .requestMatchers("/securityNone")
              .permitAll()
              .anyRequest()
              .authenticated()
              .and()
              .httpBasic(basic -> basic
                      .authenticationEntryPoint(authenticationEntryPoint));
      http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
      return http.build();
  }
}
