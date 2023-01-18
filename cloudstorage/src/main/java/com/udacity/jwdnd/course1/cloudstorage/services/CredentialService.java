package com.udacity.jwdnd.course1.cloudstorage.services;

import static com.udacity.jwdnd.course1.cloudstorage.util.EncryptionSaltUtil.createSalt;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

  @Autowired
  private CredentialMapper credentialMapper;

  @Autowired
  private EncryptionService encryptionService;

  public void addCredential(Credential credential, Integer userId) {
    credential.setUserid(userId);
    credential.setKey(createSalt());
    credential.setPassword(
        encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
    credentialMapper.insert(credential);
  }

  public List<Credential> getAllUserCredentials(Integer userid) {
    return credentialMapper.getAllCredentials(userid);
  }

  public void updateCredential(Credential credential) {
    credential.setPassword(encryptionService.encryptValue(credential.getPassword(),
        credentialMapper.getCredential(credential.getCredentialid()).getKey()));
    credentialMapper.update(credential);
  }

  public Integer deleteCredential(Integer credentialid) {
    return credentialMapper.delete(credentialid);
  }
}
