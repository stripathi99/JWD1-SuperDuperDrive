package com.udacity.jwdnd.course1.cloudstorage.services;

import static com.udacity.jwdnd.course1.cloudstorage.util.EncryptionSaltUtil.createSalt;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private HashService hashService;

  @Autowired
  private UserMapper userMapper;

  public boolean isUserNameAvailable(@NotNull String username) {
    return userMapper.getUser(username).isEmpty();
  }

  public User getUser(@NotNull String username) {
    return userMapper.getUser(username).orElse(null);
  }

  public int createUser(@NotNull User user) {
    user.setSalt(createSalt());
    user.setPassword(hashService.getHashedValue(user.getPassword(), user.getSalt()));
    return userMapper.insertUser(user);
  }
}
