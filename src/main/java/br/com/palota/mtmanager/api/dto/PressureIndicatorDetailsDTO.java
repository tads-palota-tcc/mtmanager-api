package br.com.palota.mtmanager.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PressureIndicatorDetailsDTO extends BaseDetailsDTO {

    private Long id;
    private String tag;
    private String gaugeSize;
    private String connectionSize;
    private String description;
    private Double maxGauge;
    private Double minGauge;

}
