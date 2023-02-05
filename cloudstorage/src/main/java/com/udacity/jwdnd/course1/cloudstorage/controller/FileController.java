package com.udacity.jwdnd.course1.cloudstorage.controller;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.MediaType.parseMediaType;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/download/{fileId}")
  public ResponseEntity<ByteArrayResource> downloadFileToView(@PathVariable Integer fileId) {
    File file = fileService.getFile(fileId);
    return ResponseEntity.ok()
        .contentType(parseMediaType(file.getContenttype()))
        .header(CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
        .body(new ByteArrayResource(file.getFiledata()));
  }

  @GetMapping("/delete")
  public String deleteFile(@RequestParam("id") Integer id, Model model) {
    if (fileService.removeFile(id) > 0) {
      model.addAttribute("success", true);
    } else {
      model.addAttribute("success", false);
    }
    return "result";
  }
}