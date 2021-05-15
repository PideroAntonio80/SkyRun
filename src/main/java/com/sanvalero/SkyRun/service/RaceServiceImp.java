package com.sanvalero.skyrun.service;

import com.sanvalero.skyrun.domain.Location;
import com.sanvalero.skyrun.domain.Race;
import com.sanvalero.skyrun.domain.dto.RaceDTO;
import com.sanvalero.skyrun.exception.LocationNotFoundException;
import com.sanvalero.skyrun.exception.RaceNotFoundException;
import com.sanvalero.skyrun.repository.LocationRepository;
import com.sanvalero.skyrun.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

@Service
public class RaceServiceImp implements RaceService{

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Set<Race> findAll() {
        return raceRepository.findAll();
    }

    @Override
    public Optional<Race> findById(long id) {
        return raceRepository.findById(id);
    }

    @Override
    public Race addRaceToLocation(long id, RaceDTO raceDTO) {
        Race newRace = new Race();
        setRace(newRace, raceDTO);
        newRace = raceRepository.save(newRace);
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        newRace.setLocation(location);

        raceRepository.save(newRace);

        return newRace;
    }

    @Override
    public Race modifyRace(long id, RaceDTO raceDTO) {
        Race race = raceRepository.findById(id)
                .orElseThrow(() -> new RaceNotFoundException(id));
        setRace(race, raceDTO);
        return raceRepository.save(race);
    }

    @Override
    public void deleteRace(long id) {
        Race race = raceRepository.findById(id)
                .orElseThrow(() -> new RaceNotFoundException(id));
        raceRepository.delete(race);
    }

    public void setRace(Race race, RaceDTO raceDTO) {
        race.setName(raceDTO.getName());
        race.setDistance(raceDTO.getDistance());
        race.setHeight(raceDTO.getHeight());
        race.setRaceDate(raceDTO.getRaceDate());
    }
}
