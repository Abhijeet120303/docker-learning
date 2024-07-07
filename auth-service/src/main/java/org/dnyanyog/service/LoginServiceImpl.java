package org.dnyanyog.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class LoginServiceImpl implements LoginService {

  @Autowired UsersRepository repo;

  public LoginResponse validateUser(LoginRequest request) {
    LoginResponse response = new LoginResponse();

    List<Users> user = repo.findByUserName(request.getUserName());

    if (user.size() == 1) {

      Users receivedData = user.get(0);

      String encryptedPassword = encryptAES(request.getPassword(), receivedData.getAes_Key());

      if (encryptedPassword.equalsIgnoreCase(receivedData.getPassword())) {
        response.setStatus("Success");
        response.setMessage("Login Success");

      } else {
        response.setStatus("Fail");
        response.setMessage("Username and password does not match");
      }

    } else {
      response.setStatus("Fail");
      response.setMessage("Username not present in database");
    }

    return response;
  }

  private String encryptAES(String input, String key) {
    try {
      Cipher cipher = Cipher.getInstance("AES");
      SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
      byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(encryptedBytes);

    } catch (Exception e) {
      throw new RuntimeException("Error encrypting with AES", e);
    }
  }
}
