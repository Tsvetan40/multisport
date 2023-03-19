package com.demo.multisport.dao;

import com.demo.multisport.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);

    long countUserByEmail(String email);

    Optional<User> findUserByEmail(String email);

    @Query(nativeQuery = true,
           value = "SELECT status FROM users WHERE email=:email")
    String getUserStatusByEmail(@Param("email") String email);
}
