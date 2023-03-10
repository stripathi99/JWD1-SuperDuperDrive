package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
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

  @Autowired
  private FileService fileService;

  @Autowired
  private NoteService noteService;

  @Autowired
  private CredentialService credentialService;

  @Autowired
  private EncryptionService encryptionService;

  @GetMapping("/home")
  public String home(Authentication authentication, Model model) {
    int userId = userService.getUser(authentication.getName()).getUserId();
    model.addAttribute("notes", noteService.getAllNotes(userId));
    model.addAttribute("files", fileService.getAllUserFiles(userId));
    model.addAttribute("credentials", credentialService.getAllUserCredentials(userId));
    model.addAttribute("encryptionService", encryptionService);
    return "home";
  }
}
