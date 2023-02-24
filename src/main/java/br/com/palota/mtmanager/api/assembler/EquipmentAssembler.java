package br.com.palota.mtmanager.api.assembler;

import br.com.palota.mtmanager.api.dto.EquipmentCreationDTO;
import br.com.palota.mtmanager.api.dto.EquipmentDetailsDTO;
import br.com.palota.mtmanager.api.dto.EquipmentSummaryDTO;
import br.com.palota.mtmanager.domain.model.Equipment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EquipmentAssembler {

    private final ModelMapper mapper;

    public Equipment toEntity(EquipmentCreationDTO dto) {
        return mapper.map(dto, Equipment.class);
    }

    public EquipmentDetailsDTO toDetailResponse(Equipment entity) {
        return mapper.map(entity, EquipmentDetailsDTO.class);
    }

    public Page<EquipmentSummaryDTO> toSummaryPage(Page<Equipment> entities) {
        return entities.map(e -> mapper.map(e, EquipmentSummaryDTO.class));
    }

    public void copyToEntity(EquipmentCreationDTO dto, Equipment entity) {
        mapper.map(dto, entity);
    }
}
