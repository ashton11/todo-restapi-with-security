package com.greenfoxacademy.todowithrestsecu.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthorizationTokenFilter extends OncePerRequestFilter {
  private final UserDetailsService userDetailsService;
  private final String tokenHeader;
  private final JWTTokenUtil jwtTokenUtil;

  @Autowired
  public JWTAuthorizationTokenFilter(@Qualifier(value = "jwtUserDetailsService") UserDetailsService userDetailsService, @Value("${jwt.header}") String tokenHeader, JWTTokenUtil jwtTokenUtil) {
    this.userDetailsService = userDetailsService;
    this.tokenHeader = tokenHeader;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
    logger.debug("Processing authentication for:", httpServletRequest.getRequestURL());

    final String requestHeader = httpServletRequest.getHeader(this.tokenHeader);

    String username = null;
    String authToken = null;
    if (requestHeader != null) {
      authToken = requestHeader;
      try {
        username = jwtTokenUtil.getUsernameFromToken(authToken);
      } catch (IllegalArgumentException exc) {
        logger.error("Error while getting username from token!", exc);
      } catch (ExpiredJwtException exc) {
        logger.warn("Token expired", exc);
      }
    } else {
      logger.warn("Request header empty!");
    }

    logger.debug("Checking authentication for user", username);
    UserDetails userDetails = null;
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      logger.debug("security context was null, so authorizing user");

      // It is not compelling necessary to load the use details from the database. You could also store the information
      // in the token and read it from it. It's up to you ;)
      try {
        userDetails = userDetailsService.loadUserByUsername(username);
      } catch (UsernameNotFoundException e) {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        return;
      }
    }

    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
      logger.info("authorized user '{}', setting security context", username);
      SecurityContextHolder.getContext();
    }

  }
}
