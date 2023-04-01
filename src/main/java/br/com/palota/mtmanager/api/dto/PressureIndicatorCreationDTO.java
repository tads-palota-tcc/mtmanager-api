package br.com.palota.mtmanager.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PressureIndicatorCreationDTO {

    @NotBlank
    private String tag;

    @NotBlank
    private String gaugeSize;

    @NotBlank
    private String connectionSize;

    private String description;

    private Double maxGauge;

    private Double minGauge;

    @NotNull
    @Valid
    private PlantIdDTO plant;

    private EquipmentIdDTO equipment;

}
