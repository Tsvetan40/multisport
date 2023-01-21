package com.demo.multisport.dao;

import com.demo.multisport.entities.page.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM comments WHERE center_address=:address")
    List<Comment> getCommentsByAddress(@Param("address") String address);

}
