package com.demo.multisport.dao;

import com.demo.multisport.entities.center.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
    long countCentersByAddress(String address);

    @Transactional
    void deleteCenterByAddress(String address);


    @Query(nativeQuery = true,
           value = "SELECT * FROM centers WHERE address =:address AND center_type=:type")
    Optional<Center> getCenterByAddressAndType(String address, String type);
}
