package com.cafemetrix.cafelab.defects.infrastructure.persistence.jpa.repositories;

import com.cafemetrix.cafelab.defects.domain.model.aggregates.Defect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Defect Repository
 */
@Repository
public interface DefectRepository extends JpaRepository<Defect, Long> {
    /**
     * Find all Defects by CoffeeId
     *
     * @param coffeeId The Coffee ID
     * @return A list of {@link Defect} instances related to the given coffeeId
     */
    List<Defect> findByCoffeeId(Long coffeeId);
}