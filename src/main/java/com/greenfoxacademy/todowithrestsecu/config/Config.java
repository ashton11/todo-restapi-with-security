package com.greenfoxacademy.todowithrestsecu.config;


import com.greenfoxacademy.todowithrestsecu.security.JWTAuthenticationEntryPoint;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled= true)
public class Config extends WebSecurityConfigurerAdapter {

  @Autowired
  private JWTUserDetailsService JWTuserDetailsService;

  @Autowired
  private JWTAuthenticationEntryPoint unauthorizedHandler;

  @Autowired
  private JWTAuthorizationTokenFilter authorizationTokenFilter;

  @Value("${jwt.header}")
  private String tokenHeader;

  @Value("${jwt.route.authentication.path}")
  private String authenticationPath;

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

  @Override
  protected void configure(HttpSecurity security) throws Exception{
    security
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/**")
            .permitAll()
            .anyRequest()
            .authenticated();

    security.addFilterBefore(authorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity webSec) {
    webSec
            .ignoring()
            .antMatchers(HttpMethod.POST, authenticationPath, "/register" , "/")
            .and()
            .ignoring()
            .antMatchers(HttpMethod.GET,
                    "/",
                    "/register",
                    "/*.html",
                    "/favicon.ico",
                    "/**/*.html",
                    "/**/*.css");
  }
}
