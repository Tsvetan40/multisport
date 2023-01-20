package com.demo.multisport.dao;

import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.entities.page.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
    long countCentersByAddress(String address);

    @Transactional
    void deleteCenterByAddress(String address);


    @Query(nativeQuery = true,
           value = "SELECT * FROM centers WHERE address =:address AND center_type=:type")
    Optional<Center> getCenterByAddressAndType(String address, String type);

    @Query(nativeQuery = true,
            value = "SELECT * FROM ratings r INNER JOIN centers c ON r.id = c.rating_id AND c.address=:address")
    Rating getRating(String address);

    @Query(nativeQuery = true,
            value = "SELECT * FROM comments cmnt INNER JOIN centers cntr ON cntr.address = cmnt.center_address")
    List<Comment> getComments(String address);
}
