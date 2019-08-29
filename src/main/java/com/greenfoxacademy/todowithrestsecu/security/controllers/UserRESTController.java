package com.greenfoxacademy.todowithrestsecu.security.controllers;

import com.greenfoxacademy.todowithrestsecu.models.User;
import com.greenfoxacademy.todowithrestsecu.security.JWTTokenUtil;
import com.greenfoxacademy.todowithrestsecu.security.JWTUser;
import com.greenfoxacademy.todowithrestsecu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserRESTController {

  @Value("${jwt.header}")
  private String tokenHeader;

  @Autowired
  UserService userService;

  @Autowired
  private JWTTokenUtil jwtTokenUtil;

  @Autowired
  @Qualifier("jwtUserDetailsService")
  private UserDetailsService userDetailsService;

  @PostMapping(value = "/register")
  public String createUser(@RequestBody User user){
    userService.saveUser(user);
    jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(user.getUsername()));
    return "User created";
  }

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  public JWTUser getAuthenticatedUser(HttpServletRequest request) {
    String token = request.getHeader(tokenHeader);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JWTUser user = (JWTUser) userDetailsService.loadUserByUsername(username);
    return user;
  }

}