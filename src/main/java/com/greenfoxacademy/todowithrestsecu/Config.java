package com.greenfoxacademy.todowithrestsecu;


import com.greenfoxacademy.todowithrestsecu.service.JWTUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled= true)
@Configuration
public class Config extends WebSecurityConfigurerAdapter {
  @Autowired
  private JWTUserDetailsService JWTuserDetailsService;

  @Autowired
  public void Config(AuthenticationManagerBuilder auth) throws Exception{
    auth
            .userDetailsService(JWTuserDetailsService)
            .passwordEncoder(pwEncoder());
  }

  @Bean
  public PasswordEncoder pwEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
