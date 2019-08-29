package com.greenfoxacademy.todowithrestsecu.security.controllers;

import com.greenfoxacademy.todowithrestsecu.security.JWTAuthenticationRequest;
import com.greenfoxacademy.todowithrestsecu.security.JWTAuthenticationResponse;
import com.greenfoxacademy.todowithrestsecu.security.JWTTokenUtil;
import com.greenfoxacademy.todowithrestsecu.security.JWTUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
public class AuthenticationRESTController {

  @Value("${jwt.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JWTTokenUtil jwtTokenUtil;

  @Autowired
  @Qualifier("jwtUserDetailsService")
  private UserDetailsService userDetailsService;

  @PostMapping(value = "${jwt.route.authentication.path}")
  public ResponseEntity<?> createAuthToken(@RequestBody JWTAuthenticationRequest jwtAuthenticationRequest) throws AuthenticationCredentialsNotFoundException{


    final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthenticationRequest.getUsername());
    final String token = jwtTokenUtil.generateToken(userDetails);

    authenticate(jwtAuthenticationRequest.getUsername(), jwtAuthenticationRequest.getPassword());

    return ResponseEntity.ok(new JWTAuthenticationResponse(token));
  }

  @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken;
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JWTUser user = (JWTUser) userDetailsService.loadUserByUsername(username);

    String refreshedToken = jwtTokenUtil.refreshToken(token);
    return ResponseEntity.ok(new JWTAuthenticationResponse(refreshedToken));

  }

  private void authenticate(String username, String password){
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (BadCredentialsException e) {
      throw new AuthenticationCredentialsNotFoundException("Bad credentials!", e);
    }
  }
}
