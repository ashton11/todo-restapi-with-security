package com.greenfoxacademy.todowithrestsecu.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfoxacademy.todowithrestsecu.models.User;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.greenfoxacademy.todowithrestsecu.security.SecurityConstants.EXPIRATION_TIME;
import static com.greenfoxacademy.todowithrestsecu.security.SecurityConstants.HEADER_STRING;
import static com.greenfoxacademy.todowithrestsecu.security.SecurityConstants.SECRET;
import static com.greenfoxacademy.todowithrestsecu.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager manager;


  public JWTAuthenticationFilter(AuthenticationManager manager) {
    this.manager = manager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req,
                                              HttpServletResponse res)
          throws AuthenticationException {
    try {
      User credentials = new ObjectMapper().readValue(req.getInputStream(), User.class);

      return manager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      credentials.getUsername(),
                      credentials.getPassword(),
                      new ArrayList<>()
              )
      );
    } catch (IOException e) {
      throw new RuntimeException("Something went wrong, please try again!");
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                          FilterChain chain, Authentication authResult)
          throws IOException, ServletException {
    String token = JWT.create()
            .withSubject(((User) authResult.getPrincipal()).getUsername())
            .withClaim("email", ((User) authResult.getPrincipal()).getEmail())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(SECRET));
    response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
  }
}
