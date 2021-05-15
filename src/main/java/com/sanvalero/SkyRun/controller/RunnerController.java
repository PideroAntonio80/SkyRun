package com.sanvalero.skyrun.controller;

import com.sanvalero.skyrun.domain.Runner;
import com.sanvalero.skyrun.domain.dto.RunnerDTO;
import com.sanvalero.skyrun.exception.RunnerNotFoundException;
import com.sanvalero.skyrun.service.RunnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.sanvalero.skyrun.controller.Response.NOT_FOUND;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

@RestController
@Tag(name = "Runners", description = "Runners information")
public class RunnerController {

    private final Logger logger = LoggerFactory.getLogger(RunnerController.class);

    @Autowired
    private RunnerService runnerService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get al runners list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Runners list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Runner.class)))),
            @ApiResponse(responseCode = "404", description = "Runners list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/skyrun/runners", produces = "application/json")
    public ResponseEntity<Set<Runner>> getRunners() {

        logger.info("Init getRunners");

        Set<Runner> runners = runnerService.findAll();

        logger.info("End getRunners");

        return ResponseEntity.status(HttpStatus.OK).body(runners);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get runner by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Runner exist",
                    content = @Content(schema = @Schema(implementation = Runner.class))),
            @ApiResponse(responseCode = "404", description = "Runner doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/skyrun/runners/{id}", produces = "application/json")
    public ResponseEntity<Runner> getRunnerById(@PathVariable long id) {

        logger.info("Init getRunnerById");

        Runner runner = runnerService.findById(id)
                .orElseThrow(() -> new RunnerNotFoundException(id));

        logger.info("End getRunnerById");

        return new ResponseEntity<>(runner, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new runner into a location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Runner added",
                    content = @Content(schema = @Schema(implementation = Runner.class))),
            @ApiResponse(responseCode = "404", description = "Runner couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/skyrun/races/{id}/runner", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Runner> addRunner(@PathVariable long id, @RequestBody RunnerDTO runnerDTO) {

        logger.info("Init addRunner");

        Runner addedRunner = runnerService.addRunnerToRace(id, runnerDTO);

        logger.info("End addRunner");

        return new ResponseEntity<>(addedRunner, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modify runner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Runner modified",
                    content = @Content(schema = @Schema(implementation = Runner.class))),
            @ApiResponse(responseCode = "404", description = "Runner doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/skyrun/runners/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Runner> modifyRunner(@PathVariable long id, @RequestBody RunnerDTO runnerDTO) {

        logger.info("Init modifyRunner");

        Runner runner = runnerService.modifyRunner(id, runnerDTO);

        logger.info("End modifyRunner");

        return new ResponseEntity<>(runner, HttpStatus.OK);
    }

    /*--------------------------------MODIFY_BY_FEDERATED----------------------------------*/
    @Operation(summary = "Modify runner by federated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Runner federation modified",
                    content = @Content(schema = @Schema(implementation = Runner.class))),
            @ApiResponse(responseCode = "404", description = "Runner doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/skyrun/runners/{id}/federated", produces = "application/json")
    public ResponseEntity<Runner> modifyRunnerByFederated(@PathVariable long id,
                                                          @RequestParam(value = "federated", defaultValue = "") boolean federated) {

        logger.info("Init modifyRunnerByFederated");

        Runner runner = runnerService.modifyByFederated(id, federated);

        logger.info("End modifyRunnerByFederated");

        return new ResponseEntity<>(runner, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Delete runner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Runner deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Runner doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/skyrun/runners/{id}")
    public ResponseEntity<Response> deleteRunner(@PathVariable long id) {

        logger.info("Init deleteRunner");

        runnerService.deleteRunner(id);

        logger.info("End deleteRunner");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(RunnerNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(RunnerNotFoundException rnfe){
        Response response = Response.errorResponse(NOT_FOUND, rnfe.getMessage());
        logger.error(rnfe.getMessage(), rnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
