package br.com.palota.mtmanager.api.dto;

import br.com.palota.mtmanager.domain.model.Category;
import br.com.palota.mtmanager.domain.model.EquipmentType;
import br.com.palota.mtmanager.domain.model.FluidClass;
import lombok.Data;

@Data
public class EquipmentDetailsDTO extends BaseDetailsDTO {

    private Long id;

    private String tag;

    private String name;

    private String description;

    private Double volume;

    private Double maxOperationPressure;

    private EquipmentType type;

    private FluidClass fluidClass;

    private Category category;

    private Boolean isNr13Equipment;

    private AreaSummaryDTO area;

}
