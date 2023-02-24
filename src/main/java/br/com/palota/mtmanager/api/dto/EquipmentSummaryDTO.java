package br.com.palota.mtmanager.api.dto;

import br.com.palota.mtmanager.domain.model.Category;
import br.com.palota.mtmanager.domain.model.EquipmentType;
import lombok.Data;

@Data
public class EquipmentSummaryDTO extends BaseDetailsDTO {

    private Long id;

    private String tag;

    private String name;

    private EquipmentType type;

    private Category category;

    private AreaSummaryDTO area;

}
