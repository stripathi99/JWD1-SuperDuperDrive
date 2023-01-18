package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home/credentials")
public class CredentialController {

  @Autowired
  private CredentialService credentialService;

  @Autowired
  private UserService userService;

  @PostMapping
  public String createUpdateCredential(Authentication authentication, Credential credential,
      Model model) {
    if (credential.getCredentialid() != null) {
      credentialService.updateCredential(credential);
    } else {
      credentialService.addCredential(credential,
          userService.getUser(authentication.getName()).getUserId());
    }
    model.addAttribute("success", true);
    return "result";
  }

  @GetMapping("/delete")
  public String deleteCredential(@RequestParam("id") Integer credentialid, Model model) {
    if (credentialService.deleteCredential(credentialid) > 0) {
      model.addAttribute("success", true);
    } else {
      model.addAttribute("success", false);
    }
    return "result";
  }
}
