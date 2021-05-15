package com.sanvalero.skyrun.service;

import com.sanvalero.skyrun.domain.Race;
import com.sanvalero.skyrun.domain.dto.RaceDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

public interface RaceService {

    Set<Race> findAll();
    Optional<Race> findById(long id);

    Race addRaceToLocation(long id, RaceDTO raceDTO);
    Race modifyRace(long id, RaceDTO raceDTO);
    void deleteRace(long id);
}
