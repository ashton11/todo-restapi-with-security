package com.greenfoxacademy.todowithrestsecu.config;


import com.greenfoxacademy.todowithrestsecu.security.JWTAuthenticationEntryPoint;
import com.greenfoxacademy.todowithrestsecu.security.JWTAuthentinticationFilter;
import com.greenfoxacademy.todowithrestsecu.security.JWTAuthorizationFilter;
import com.greenfoxacademy.todowithrestsecu.security.JWTAuthorizationTokenFilter;

import com.greenfoxacademy.todowithrestsecu.security.JWTUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {

  private UserDetailsService userDetailsService;

  private BCryptPasswordEncoder pwEncoder;

  @Autowired
  public Config(UserDetailsService userDetailsService,
                BCryptPasswordEncoder pwEncoder){
          this.pwEncoder = pwEncoder;
          this.userDetailsService = userDetailsService;
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception{
    auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(pwEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http
            .csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST, "/register")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .addFilter(new JWTAuthorizationFilter(authenticationManager()))
            .addFilter(new JWTAuthentinticationFilter(authenticationManager()));
  }
}
