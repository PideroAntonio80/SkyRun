package com.sanvalero.skyrun.service;

import com.sanvalero.skyrun.domain.Race;
import com.sanvalero.skyrun.domain.Runner;
import com.sanvalero.skyrun.domain.dto.RunnerDTO;
import com.sanvalero.skyrun.exception.RaceNotFoundException;
import com.sanvalero.skyrun.exception.RunnerNotFoundException;
import com.sanvalero.skyrun.repository.RaceRepository;
import com.sanvalero.skyrun.repository.RunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

@Service
public class RunnerServiceImp implements RunnerService{

    @Autowired
    private RunnerRepository runnerRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Override
    public Set<Runner> findAll() {
        return runnerRepository.findAll();
    }

    @Override
    public Optional<Runner> findById(long id) {
        return runnerRepository.findById(id);
    }

    @Override
    public Runner addRunnerToRace(long id, RunnerDTO runnerDTO) {
        Runner newRunner = new Runner();
        setRunner(newRunner, runnerDTO);
        newRunner = runnerRepository.save(newRunner);
        Race race = raceRepository.findById(id)
                .orElseThrow(() -> new RaceNotFoundException(id));
        newRunner.setRace(race);

        runnerRepository.save(newRunner);

        return newRunner;
    }

    @Override
    public Runner modifyRunner(long id, RunnerDTO runnerDTO) {
        Runner runner = runnerRepository.findById(id)
                .orElseThrow(() -> new RunnerNotFoundException(id));
        setRunner(runner, runnerDTO);
        return runnerRepository.save(runner);
    }

    @Override
    public Runner modifyByFederated(long id, boolean federated) {
        Runner runner = runnerRepository.findById(id)
                .orElseThrow(() -> new RunnerNotFoundException(id));
        runner.setFederated(federated);
        return runnerRepository.save(runner);
    }

    @Override
    public void deleteRunner(long id) {
        Runner runner = runnerRepository.findById(id)
                .orElseThrow(() -> new RunnerNotFoundException(id));
        runnerRepository.delete(runner);
    }

    public void setRunner(Runner runner, RunnerDTO runnerDTO) {
        runner.setName(runnerDTO.getName());
        runner.setAge(runnerDTO.getAge());
        runner.setFederated(runnerDTO.isFederated());
    }
}
