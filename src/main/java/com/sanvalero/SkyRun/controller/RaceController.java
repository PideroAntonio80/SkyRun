package com.sanvalero.skyrun.controller;

import com.sanvalero.skyrun.domain.Race;
import com.sanvalero.skyrun.domain.dto.RaceDTO;
import com.sanvalero.skyrun.exception.RaceNotFoundException;
import com.sanvalero.skyrun.service.RaceService;
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
@Tag(name = "races", description = "Races information")
public class RaceController {

    private final Logger logger = LoggerFactory.getLogger(RaceController.class);

    @Autowired
    private RaceService raceService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get al races list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Races list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Race.class)))),
            @ApiResponse(responseCode = "404", description = "Races list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/skyrun/races", produces = "application/json")
    public ResponseEntity<Set<Race>> getRaces() {

        logger.info("Init getRaces");

        Set<Race> races = raceService.findAll();

        logger.info("End getRaces");

        return ResponseEntity.status(HttpStatus.OK).body(races);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get race by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Race exist",
                    content = @Content(schema = @Schema(implementation = Race.class))),
            @ApiResponse(responseCode = "404", description = "Race doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/skyrun/races/{id}", produces = "application/json")
    public ResponseEntity<Race> getRaceById(@PathVariable long id) {

        logger.info("Init getRaceById");

        Race race = raceService.findById(id)
                .orElseThrow(() -> new RaceNotFoundException(id));

        logger.info("End getRaceById");

        return new ResponseEntity<>(race, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new race into a location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Race added",
                    content = @Content(schema = @Schema(implementation = Race.class))),
            @ApiResponse(responseCode = "404", description = "Race couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/skyrun/locations/{id}/race", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Race> addRace(@PathVariable long id, @RequestBody RaceDTO raceDTO) {

        logger.info("Init addRace");

        Race addedRace = raceService.addRaceToLocation(id, raceDTO);

        logger.info("End addRace");

        return new ResponseEntity<>(addedRace, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modify race")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Race modified",
                    content = @Content(schema = @Schema(implementation = Race.class))),
            @ApiResponse(responseCode = "404", description = "Race doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/skyrun/races/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Race> modifyRace(@PathVariable long id, @RequestBody RaceDTO raceDTO) {

        logger.info("Init modifyRace");

        Race race = raceService.modifyRace(id, raceDTO);

        logger.info("End modifyRace");

        return new ResponseEntity<>(race, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Delete race")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Race deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Race doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/skyrun/races/{id}")
    public ResponseEntity<Response> deleteRace(@PathVariable long id) {

        logger.info("Init deleteRace");

        raceService.deleteRace(id);

        logger.info("End deleteRace");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(RaceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(RaceNotFoundException rnfe){
        Response response = Response.errorResponse(NOT_FOUND, rnfe.getMessage());
        logger.error(rnfe.getMessage(), rnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
