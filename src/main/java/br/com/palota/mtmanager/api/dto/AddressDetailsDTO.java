package br.com.palota.mtmanager.api.dto;

import br.com.palota.mtmanager.domain.model.State;
import lombok.Data;

@Data
public class AddressDetailsDTO {

    private String streetName;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private State state;
    private String zipCode;

}
