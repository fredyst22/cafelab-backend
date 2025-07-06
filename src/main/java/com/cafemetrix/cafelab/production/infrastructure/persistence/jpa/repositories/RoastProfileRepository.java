package com.cafemetrix.cafelab.production.infrastructure.persistence.jpa.repositories;

import com.cafemetrix.cafelab.production.domain.model.aggregates.RoastProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoastProfileRepository extends JpaRepository<RoastProfile, Long> {
    List<RoastProfile> findByUserId(Long userId);
    List<RoastProfile> findByCoffeeLotId(Long coffeeLotId);
    List<RoastProfile> findByUserIdAndCoffeeLotId(Long userId, Long coffeeLotId);
} 