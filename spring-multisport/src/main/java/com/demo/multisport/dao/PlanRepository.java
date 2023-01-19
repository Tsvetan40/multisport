package com.demo.multisport.dao;

import com.demo.multisport.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PlanRepository extends JpaRepository<Plan, Long> {
}
