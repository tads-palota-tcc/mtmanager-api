package br.com.palota.mtmanager.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlantCreationDTO {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @Valid
    private AddressCreationDTO address;
}
