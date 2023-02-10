package br.com.palota.mtmanager.api.dto;

import br.com.palota.mtmanager.domain.model.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressCreationDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    private String streetName;

    @NotBlank
    @Size(min = 1, max = 10)
    private String number;

    private String complement;

    private String neighborhood;

    @NotBlank
    @Size(min = 2, max = 100)
    private String city;

    @NotNull
    private State state;

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String zipCode;

}
