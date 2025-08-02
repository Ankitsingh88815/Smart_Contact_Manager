package com.scm.scm20.services;

import java.util.List;
import java.util.Optional;

import com.scm.scm20.Entities.UserClient;

public interface UserService {

    UserClient saveUser(UserClient userclient);

    Optional<UserClient> getUserById(String id);
    
    Optional<UserClient> updateUser(UserClient userclient);
    
    void deleteUser( String id);
    
    boolean isUSerExist(String userId);
    
    boolean isUserExistByEmail(String email);

    List<UserClient> getAllUsers();
}
