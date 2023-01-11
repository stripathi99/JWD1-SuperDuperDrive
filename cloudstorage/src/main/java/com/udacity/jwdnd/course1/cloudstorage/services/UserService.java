package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private HashService hashService;

  @Autowired
  private UserMapper userMapper;

  public boolean isUserNameAvailable(String username) {
    return Objects.isNull(userMapper.getUser(username));
  }

  public User getUser(String username) {
    return userMapper.getUser(username);
  }

  public int createUser(@NotNull User user) {
    String encodedSalt = createSalt();
    String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

    return userMapper.insertUser(
        new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstname(),
            user.getLastname()));
  }

  private String createSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return Base64.getEncoder().encodeToString(salt);
  }
}
