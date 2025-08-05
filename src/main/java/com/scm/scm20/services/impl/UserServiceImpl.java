package com.scm.scm20.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.scm20.Entities.UserClient;
import com.scm.scm20.helper.AppConstants;
import com.scm.scm20.helper.ResouceNotFoundException;
import com.scm.scm20.repositories.UserRepo;
import com.scm.scm20.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
 
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserClient saveUser(UserClient userClient) {
        if (userRepo.existsByEmail(userClient.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }

        if (userClient.getUserId() == null || userClient.getUserId().isBlank()) {
            userClient.setUserId(UUID.randomUUID().toString());
        }
        
        System.out.println("Password after encoding: " + userClient.getPassword());
        // encode the password before saving
        userClient.setPassword(passwordEncoder.encode(userClient.getPassword()));

        userClient.setRoleList(List.of(AppConstants.ROLE_USER)); // Default role for new users
        // encode the password
        System.out.println("Password before encoding: " + userClient.getPassword());
        logger.info(userClient.getProviders().toString());
        return userRepo.save(userClient);
    }

    // @Override
    // public UserClient saveUser(UserClient userclient) {
    // if (userclient.getUserId() == null || userclient.getUserId().isBlank()) {
    // String userId = UUID.randomUUID().toString();
    // userclient.setUserId(userId);
    // }
    
    // return userRepo.save(userclient);
    // }

    // @Override
    // public UserClient saveUser(UserClient userclient) {
    // String userId = UUID.randomUUID().toString();
    // userclient.setUserId(userId);

    // return userRepo.save(userclient);
    // }

    @Override
    public Optional<UserClient> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<UserClient> updateUser(UserClient userclient) {
        UserClient user = userRepo.findById(userclient.getUserId())
                .orElseThrow(() -> new ResouceNotFoundException("User not found"));
        // update userclient from the user
        user.setName(userclient.getName());
        user.setEmail(userclient.getEmail());
        user.setPassword(userclient.getPassword());
        user.setAbout(userclient.getAbout());
        user.setProfilePic(userclient.getProfilePic());
        user.setPhoneNumber(userclient.getPhoneNumber());
        user.setEnabled(userclient.isEnabled());
        user.setEmailVerified(userclient.isEmailVerified());
        user.setPhoneVerified(userclient.isPhoneVerified());
        user.setProviders(userclient.getProviders());
        user.setProviderUserId(userclient.getProviderUserId());

        UserClient save = userRepo.save(user);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        UserClient user = userRepo.findById(id).orElseThrow(() -> new ResouceNotFoundException("user not found"));
        userRepo.delete(user);
    }

    @Override
    public boolean isUSerExist(String userId) {
        UserClient user = userRepo.findById(userId).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        UserClient user = userRepo.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public List<UserClient> getAllUsers() {
        return userRepo.findAll();
    }

}
