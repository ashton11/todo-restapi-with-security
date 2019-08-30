package com.greenfoxacademy.todowithrestsecu.config;


import com.greenfoxacademy.todowithrestsecu.security.JWTAuthenticationFilter;
import com.greenfoxacademy.todowithrestsecu.security.JWTAuthorizationFilter;

import com.greenfoxacademy.todowithrestsecu.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {

  private UserDetailsService userDetailsService;

  private BCryptPasswordEncoder pwEncoder;

  @Autowired
  public Config(UserDetailsServiceImpl userDetailsService,
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
            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }
}
