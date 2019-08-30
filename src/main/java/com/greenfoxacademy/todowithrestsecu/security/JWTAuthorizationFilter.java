package com.greenfoxacademy.todowithrestsecu.security;

import org.springframework.security.authentication.AuthenticationManager;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter implements Filter {
  public JWTAuthorizationFilter(AuthenticationManager manager) {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

  }
}
