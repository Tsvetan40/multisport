package com.demo.multisport.dao;

import com.demo.multisport.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    long countUserByEmail(String email);

}
