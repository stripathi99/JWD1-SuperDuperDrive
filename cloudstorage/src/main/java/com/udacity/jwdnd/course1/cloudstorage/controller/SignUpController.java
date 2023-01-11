package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

  @Autowired
  private UserService userService;

  @GetMapping
  public String signup() {
    return "signup";
  }

  @PostMapping
  public String register(@ModelAttribute User user, Model model) {
    String error = null;

    if (!userService.isUserNameAvailable(user.getUsername())) {
      error = "The username: '" + user.getUsername() + "' already exists.";
    }

    if (Objects.isNull(error)) {
      int newUser = userService.createUser(user);
      if (newUser < 0) {
        error = "There was an error signing you up. Please try again.";
      }
    }

    if (!Objects.isNull(error)) {
      model.addAttribute("signupError", error);
      return "signup";
    }

    model.addAttribute("signupSuccess", true);
    return "login";
  }
}
