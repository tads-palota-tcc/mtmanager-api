package br.com.palota.mtmanager.api.assembler;

import br.com.palota.mtmanager.api.dto.PlantCreationDTO;
import br.com.palota.mtmanager.api.dto.PlantDetailsDTO;
import br.com.palota.mtmanager.api.dto.PlantSummaryDTO;
import br.com.palota.mtmanager.domain.model.Plant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PlantAssembler {

    private final ModelMapper mapper;

    public Plant toEntity(PlantCreationDTO dto) {
        return mapper.map(dto, Plant.class);
    }

    public PlantDetailsDTO toDetailResponse(Plant entity) {
        return mapper.map(entity, PlantDetailsDTO.class);
    }

    public Page<PlantSummaryDTO> toSummaryPage(Page<Plant> entities) {
        return entities.map(e -> mapper.map(e, PlantSummaryDTO.class));
    }

    public void copyToEntity(PlantCreationDTO dto, Plant entity) {
        mapper.map(dto, entity);
    }
}
