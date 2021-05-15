package com.sanvalero.skyrun.service;

import com.sanvalero.skyrun.domain.Location;
import com.sanvalero.skyrun.domain.Race;
import com.sanvalero.skyrun.domain.dto.LocationDTO;
import com.sanvalero.skyrun.exception.LocationNotFoundException;
import com.sanvalero.skyrun.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

@Service
public class LocationServiceImp implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Set<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(long id) {
        return locationRepository.findById(id);
    }

    @Override
    public Set<Race> findRacesByDistanceAndHeight(long id, float minDistance, float maxDistance, float minHeight, float maxHeight) {
        Location location = findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        List<Race> myRaces = location.getRaces();
        Set<Race> races = myRaces.stream()
                .filter(myRace -> myRace.getDistance() >= minDistance && myRace.getDistance() <= maxDistance)
                .filter(myRace -> myRace.getHeight() >= minHeight && myRace.getHeight() <= maxHeight)
                .collect(Collectors.toSet());
        return races;
    }

    @Override
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location modifyLocation(long id, LocationDTO locationDTO) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        setLocation(location, locationDTO);
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        locationRepository.delete(location);
    }

    public void setLocation(Location location, LocationDTO locationDTO) {
        location.setName(locationDTO.getName());
        location.setValley(locationDTO.getValley());
    }
}
