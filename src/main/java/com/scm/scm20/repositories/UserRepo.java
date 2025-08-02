package com.scm.scm20.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.scm20.Entities.UserClient;

public interface UserRepo extends JpaRepository<UserClient,String> {

    Optional<UserClient> findByEmail(String email);

    //boolean existByEmail(String email);
    Optional<UserClient> findByEmailAndPassword(String email, String password);
    
    boolean existsByEmail(String email);
}
