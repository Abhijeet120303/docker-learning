package org.dnyanyog.service;

import java.util.List;
import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.entity.Users;

public interface UserManagementService {
  public AddUserResponse addUser(AddUserRequest request);

  public AddUserResponse searchUser(Long userId);

  public List<Users> getAllUser();
}
