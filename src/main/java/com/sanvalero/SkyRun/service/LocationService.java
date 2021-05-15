package com.sanvalero.skyrun.service;

import com.sanvalero.skyrun.domain.Location;
import com.sanvalero.skyrun.domain.Race;
import com.sanvalero.skyrun.domain.dto.LocationDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

public interface LocationService {

    Set<Location> findAll();
    Optional<Location> findById(long id);
    Set<Race> findRacesByDistanceAndHeight(long id, float minDistance, float maxDistance, float minHeight, float maxHeight);

    Location addLocation(Location location);
    Location modifyLocation(long id, LocationDTO locationDTO);
    void deleteLocation(long id);
}
