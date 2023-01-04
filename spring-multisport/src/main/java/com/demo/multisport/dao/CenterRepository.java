package com.demo.multisport.dao;

import com.demo.multisport.entities.center.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
    long countCentersByAddress(String address);

    @Transactional
    void deleteCenterByAddress(String address);
}
