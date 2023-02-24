package br.com.palota.mtmanager.api.dto;

import lombok.Data;

@Data
public class AreaDetailsDTO extends BaseDetailsDTO {

    private Long id;

    private String name;

    private String code;

    private PlantSummaryDTO plant;

}
