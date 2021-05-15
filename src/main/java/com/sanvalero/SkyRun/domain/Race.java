package com.sanvalero.skyrun.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "race")
public class Race {

    @Schema(description = "Race identification number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Race name", example = "Km vertical Ibon de Plan", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Race distance in metres", example = "60000")
    @Column
    private float distance;

    @Schema(description = "Race cumulated altitude gain in metres", example = "4500")
    @Column
    private float height;

    @Schema(description = "Race date", example = "08/07/2021")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate raceDate;

    @Schema(description = "Race's Location identity")
    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonBackReference
    private Location location;

    @Schema(description = "Race´s runners list")
    @OneToMany(mappedBy = "race", cascade = CascadeType.REMOVE)
    private List<Runner> runners;
}
