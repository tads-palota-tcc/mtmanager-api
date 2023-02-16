package br.com.palota.mtmanager.api.assembler;

import br.com.palota.mtmanager.api.dto.PlantCreationDTO;
import br.com.palota.mtmanager.api.dto.PlantDetailsDTO;
import br.com.palota.mtmanager.api.dto.PlantSummaryDTO;
import br.com.palota.mtmanager.domain.model.Plant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlantAssembler {

    private final ModelMapper modelMapper;

    public Plant fromDTO(PlantCreationDTO dto) {
        return modelMapper.map(dto, Plant.class);
    }

    public PlantDetailsDTO fromEntity(Plant entity) {
        return modelMapper.map(entity, PlantDetailsDTO.class);
    }

    public Page<PlantSummaryDTO> fromEntityPage(Page<Plant> entities) {
        return entities.map(e -> modelMapper.map(e, PlantSummaryDTO.class));
    }
}
