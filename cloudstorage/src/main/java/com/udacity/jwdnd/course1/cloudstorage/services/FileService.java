package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

  @Autowired
  private FileMapper fileMapper;

  public List<File> getAllUserFiles(int userId) {
    return fileMapper.getAllFilesByUser(userId);
  }

  public int uploadNewFile(MultipartFile multipartFile, Integer userId) throws IOException {
    return fileMapper.insertFile(new File(multipartFile.getName(),
        multipartFile.getContentType(),
        Long.toString(multipartFile.getSize()),
        userId,
        multipartFile.getBytes()));
  }

  public int removeFile(int fileId) {
    return fileMapper.deleteFile(fileId);
  }
}
