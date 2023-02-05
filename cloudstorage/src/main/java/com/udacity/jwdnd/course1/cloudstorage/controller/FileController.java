package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/home/files")
public class FileController {

  @Autowired
  private FileService fileService;

  @Autowired
  private UserService userService;

  @PostMapping
  public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile,
      Authentication authentication, Model model)
      throws IOException {
    fileService.uploadNewFile(multipartFile,
        userService.getUser(authentication.getName()).getUserId());
    model.addAttribute("success", true);
    return "result";
  }
}