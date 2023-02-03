package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NoteMapper {

  @Insert("INSERT INTO NOTES (notetitle, notedescription, userid)"
      + " VALUES (#{notetitle}, #{notedescription}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "noteid")
  Integer insertNote(Note note);

  @Select("SELECT * FROM NOTES WHERE noteid=#{noteid}")
  Optional<Note> getNote(Integer noteid);

  @Select("SELECT * FROM NOTES WHERE userid=#{userid} ORDER BY noteid DESC")
  List<Note> getAllNotesBy(Integer userid);

  @Update("UPDATE NOTES SET notetitle=#{notetitle}, notedescription=#{notedescription}"
      + " WHERE noteid=#{noteid}")
  void updateNote(Note note);

  @Delete("DELETE FROM NOTES WHERE noteid=#{noteid}")
  Integer deleteNote(Integer noteid);
}
