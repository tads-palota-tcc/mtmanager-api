package br.com.palota.mtmanager.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PlantCreationDTO {

    @NotBlank
    @Size(min = 1, max = 20)
    private String code;

    @NotBlank
    @Size(min = 3, max = 60)
    private String name;

    @NotNull
    @Valid
    private AddressCreationDTO address;
}
