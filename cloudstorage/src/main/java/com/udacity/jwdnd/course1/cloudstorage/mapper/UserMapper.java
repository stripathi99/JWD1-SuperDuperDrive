package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import java.util.Optional;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT * FROM USERS WHERE username=#{username}")
  Optional<User> getUser(String username);

  @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) "
      + "VALUES (#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
  @Options(useGeneratedKeys = true, keyProperty = "userId")
  Integer insertUser(User user);
}