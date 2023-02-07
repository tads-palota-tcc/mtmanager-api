package br.com.palota.mtmanager.api.dto;

import br.com.palota.mtmanager.domain.model.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressCreationDTO {

    @NotBlank
    private String streetName;

    @NotBlank
    private String number;

    private String complement;

    private String neighborhood;

    @NotBlank
    private String city;

    @NotNull
    private State state;

    @NotBlank
    private String zipCode;

}
