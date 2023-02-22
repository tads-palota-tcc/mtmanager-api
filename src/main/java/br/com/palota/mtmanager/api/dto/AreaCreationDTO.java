package br.com.palota.mtmanager.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AreaCreationDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @Size(min = 1, max = 20)
    private String code;

    @NotNull
    @Valid
    private PlantIdDTO plant;

}
