package com.demo.multisport.dao;

import com.demo.multisport.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmailAndPassword(String email, String password);

    @Query(nativeQuery = true,
        value = "SELECT salt FROM users WHERE email =:email")
    String getSaltByEmail(@Param("email") String email);

    long countUserByEmail(String email);

}
