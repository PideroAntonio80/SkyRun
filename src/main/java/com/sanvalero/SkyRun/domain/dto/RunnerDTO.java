package com.sanvalero.skyrun.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

@Data
@NoArgsConstructor
public class RunnerDTO {

    @Schema(description = "Runner complete name", example = "Johnny Lawrence", required = true)
    private String name;

    @Schema(description = "Runner age", example = "40", required = true)
    private int age;

    @Schema(description = "Is this runner federated?", example = "true")
    private boolean federated;
}
