package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

  @Autowired
  private NoteMapper noteMapper;

  public boolean isNoteAvailable(Integer noteid) {
    return noteMapper.getNote(noteid).isPresent();
  }

  public int addNewNode(Note note, Integer userid) {
    note.setUserid(userid);
    System.out.println("Note to be added: " + note);
    return noteMapper.insertNote(note);
  }

  public List<Note> getAllNotes(Integer userid) {
    for(Note note: noteMapper.getAllNotesBy(userid)) {
      System.out.println(note);
    }
    return noteMapper.getAllNotesBy(userid);
  }

  public void updateNote(Note node) {
    noteMapper.updateNote(node);
  }

  public Integer deleteNode(Integer noteid) {
    return noteMapper.deleteNote(noteid);
  }
}
