package com.sanvalero.skyrun.repository;

import com.sanvalero.skyrun.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    Set<Location> findAll();
}
