package com.cafemetrix.cafelab.coffees.infrastructure.persistence.jpa.repositories;

import com.cafemetrix.cafelab.coffees.domain.model.aggregates.Coffee;
import com.cafemetrix.cafelab.coffees.domain.model.valueobjects.CoffeeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Coffee Repository
 */
@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    /**
     * Find a Coffee by Name
     *
     * @param name The Coffee Name
     * @return A {@link Coffee} instance if the name is valid, otherwise empty
     */
    Optional<Coffee> findByName(CoffeeName name);

    /**
     * Check if a Coffee exists by Name
     *
     * @param name The Coffee Name
     * @return True if the name exists, otherwise false
     */
    boolean existsByName(CoffeeName name);
}