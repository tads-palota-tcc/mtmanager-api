package br.com.palota.mtmanager.api.assembler;

import br.com.palota.mtmanager.api.dto.AreaCreationDTO;
import br.com.palota.mtmanager.api.dto.AreaDetailsDTO;
import br.com.palota.mtmanager.api.dto.AreaSummaryDTO;
import br.com.palota.mtmanager.domain.model.Area;
import br.com.palota.mtmanager.domain.model.Plant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AreaAssembler {

    private final ModelMapper mapper;

    public Area toEntity(AreaCreationDTO dto) {
        return mapper.map(dto, Area.class);
    }

    public AreaDetailsDTO toDetailResponse(Area entity) {
        return mapper.map(entity, AreaDetailsDTO.class);
    }

    public Page<AreaSummaryDTO> toSummaryPage(Page<Area> entities) {
        return entities.map(e -> mapper.map(e, AreaSummaryDTO.class));
    }

    public void copyToEntity(AreaCreationDTO dto, Area entity) {
        entity.setPlant(new Plant());
        mapper.map(dto, entity);
    }
}
