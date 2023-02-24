package br.com.palota.mtmanager.api.dto;

import lombok.Data;

@Data
public class PlantDetailsDTO extends BaseDetailsDTO {

    private Long id;

    private String code;

    private String name;

    private AddressDetailsDTO address;

}
