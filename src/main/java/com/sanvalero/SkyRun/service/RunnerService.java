package com.sanvalero.skyrun.service;

import com.sanvalero.skyrun.domain.Runner;
import com.sanvalero.skyrun.domain.dto.RunnerDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

public interface RunnerService {

    Set<Runner> findAll();
    Optional<Runner> findById(long id);

    Runner addRunnerToRace(long id, RunnerDTO runnerDTO);
    Runner modifyRunner(long id, RunnerDTO runnerDTO);
    Runner modifyByFederated(long id, boolean federated);
    void deleteRunner(long id);
}
