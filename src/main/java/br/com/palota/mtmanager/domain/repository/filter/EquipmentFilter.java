package br.com.palota.mtmanager.domain.repository.filter;

import br.com.palota.mtmanager.domain.model.EquipmentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentFilter {

    private String tag;
    private String name;
    private String description;
    private String areaCode;
    private String plantCode;
    private EquipmentType type;
    private String status;

}
