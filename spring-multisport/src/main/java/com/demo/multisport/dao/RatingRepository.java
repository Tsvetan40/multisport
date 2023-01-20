package com.demo.multisport.dao;

import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.page.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM centers c INNER JOIN ratings r on c.address=:address")
    Optional<Center> getCenter(@Param("address") String address);
}
