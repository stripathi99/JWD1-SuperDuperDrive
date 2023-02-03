package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CredentialMapper {

  // insert
  @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid)"
      + " VALUES (#{url}, #{username}, #{key}, #{password}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "credentialid")
  void insert(Credential credential);

  // select - 1
  @Select("SELECT * FROM CREDENTIALS WHERE credentialid=${credentialid}")
  Credential getCredential(Integer credentialid);

  // select - all
  @Select("SELECT * FROM CREDENTIALS WHERE userid=${userid} ORDER BY credentialid DESC")
  List<Credential> getAllCredentials(Integer userid);

  // update
  @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, password=#{password}"
      + " WHERE credentialid=${credentialid}")
  void update(Credential credential);

  // delete
  @Delete("DELETE FROM CREDENTIALS WHERE credentialid=#{credentialid}")
  Integer delete(Integer credentialid);
}
