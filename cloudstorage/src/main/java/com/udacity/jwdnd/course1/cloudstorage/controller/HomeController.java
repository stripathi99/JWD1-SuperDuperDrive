package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  private UserService userService;

  @GetMapping("/home")
  public String home(Authentication authentication, Model model) {
    var UID = userService.getUser(authentication.getName()).getUserId().toString();
    return "home";
  }
}
