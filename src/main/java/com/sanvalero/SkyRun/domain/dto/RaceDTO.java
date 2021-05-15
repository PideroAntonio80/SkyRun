package com.sanvalero.skyrun.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

@Data
@NoArgsConstructor
public class RaceDTO {

    @Schema(description = "Race name", example = "Km vertical Ibon de Plan", required = true)
    private String name;

    @Schema(description = "Race distance in metres", example = "60000")
    private float distance;

    @Schema(description = "Race cumulated altitude gain in metres", example = "4500")
    private float height;

    @Schema(description = "Race date", example = "08/07/2021")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate raceDate;
}
