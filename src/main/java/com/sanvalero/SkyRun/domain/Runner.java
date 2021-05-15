package com.sanvalero.skyrun.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "runner")
public class Runner {

    @Schema(description = "Runner identification number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Runner complete name", example = "Johnny Lawrence", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Runner age", example = "40", required = true)
    @NotBlank
    @Column
    private int age;

    @Schema(description = "Is this runner federated?", example = "true")
    @NotBlank
    @Column
    private boolean federated;

    @Schema(description = "Runner's race identity")
    @ManyToOne
    @JoinColumn(name = "race_id")
    @JsonBackReference
    private Race race;
}
