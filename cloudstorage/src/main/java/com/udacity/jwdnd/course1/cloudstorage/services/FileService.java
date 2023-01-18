package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

  @Autowired
  private FileMapper fileMapper;

  public List<File> getAllUserFiles(int userId) {
    return fileMapper.getAllFilesByUser(userId);
  }

  public int uploadNewFile(File file) {
    return fileMapper.insertFile(file);
  }

  public int removeFile(int fileId) {
    return fileMapper.deleteFile(fileId);
  }
}
