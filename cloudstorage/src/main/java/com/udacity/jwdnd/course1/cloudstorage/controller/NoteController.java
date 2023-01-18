package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home/notes")
public class NoteController {

  @Autowired
  private UserService userService;

  @Autowired
  private NoteService noteService;

  @PostMapping
  public String createUpdateNote(Authentication authentication, Note note, Model model) {
    System.out.println("inside createUpdateNote - " + note);

    if (note.getNoteid() != null) {
      noteService.updateNote(note);
    } else {
      noteService.addNewNode(note, userService.getUser(authentication.getName()).getUserId());
    }

    model.addAttribute("success", true);
    return "result";
  }

  @GetMapping("/delete")
  public String deleteNote(@RequestParam("id") Integer id, Model model) {
    if (noteService.deleteNode(id) > 0) {
      model.addAttribute("success", true);
    } else {
      model.addAttribute("success", false);
    }
    return "result";
  }
}
