package com.demo.multisport.dao;

import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.entities.page.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
    long countCentersByAddress(String address);

    @Transactional
    Optional<Center> deleteCenterByAddress(String address);

    @Query(nativeQuery = true,
           value = "SELECT * FROM centers WHERE id=:id AND center_type='SportCenter';")
    Optional<SportCenter> getSportCenterByAddress(@Param("id") Long id);

    @Query(nativeQuery = true,
            value = "SELECT * FROM centers WHERE id=:id AND center_type='RelaxCenter';")
    Optional<RelaxCenter> getRelaxCenterByAddress(@Param("id") Long id);

    @Query(nativeQuery = true,
            value = "select ratings.id, ratings.rate\n" +
                    "from ratings\n" +
                    "inner join centers on ratings.id = centers.rating_id where centers.address=:address ;")
    Rating getRating(@Param("address") String address);

    @Query(nativeQuery = true, value = "SELECT * FROM centers WHERE address=:address AND center_type=:type;")
    Optional<Center> getCenterByAddressAndType(@Param("address") String address,
                                               @Param("type") String type);

    @Query(nativeQuery = true, value = "SELECT * FROM centers WHERE center_type='SportCenter';")
    List<SportCenter> getAllSportCenters();

    @Query(nativeQuery = true, value = "SELECT * FROM centers WHERE center_type='RelaxCenter';")
    List<RelaxCenter> getAllRelaxCenters();

    Optional<Center> getCenterByAddress(String center);
}
