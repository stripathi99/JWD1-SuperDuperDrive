package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

  @GetMapping("/logout-success")
  public String logout(Model model) {
    model.addAttribute("logoutSuccess", true);
    return "login";
  }
}