package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {

  @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)"
      + " VALUES (#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
  @Options(useGeneratedKeys = true, keyProperty = "fileId")
  void insertFile(File file);

  @Select("SELECT * FROM FILES WHERE userid=#{userid} ORDER BY fileId DESC")
  List<File> getAllFilesByUser(int userid);

  @Select("SELECT * FROM FILES WHERE fileId=#{fileId} ORDER BY fileId DESC")
  File get(Integer fileId);

  @Delete("DELETE FROM FILES WHERE fileId=#{fileId}")
  int deleteFile(int fileId);
}