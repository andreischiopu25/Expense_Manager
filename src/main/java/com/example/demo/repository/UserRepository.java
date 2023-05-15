package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //SELECT * FROM tbl_users WHERE email = ?
    Optional<User> findByEmail(String email);
}
