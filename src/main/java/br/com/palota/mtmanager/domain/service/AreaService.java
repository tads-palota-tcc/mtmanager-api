package br.com.palota.mtmanager.domain.service;

import br.com.palota.mtmanager.api.dto.AreaCreationDTO;
import br.com.palota.mtmanager.api.dto.AreaDetailsDTO;
import br.com.palota.mtmanager.api.dto.AreaSummaryDTO;
import br.com.palota.mtmanager.core.Constants;
import br.com.palota.mtmanager.domain.exception.BusinessException;
import br.com.palota.mtmanager.domain.exception.PlantNotFoundException;
import br.com.palota.mtmanager.domain.model.Area;
import br.com.palota.mtmanager.domain.model.Plant;
import br.com.palota.mtmanager.domain.repository.AreaRepository;
import br.com.palota.mtmanager.domain.repository.filter.AreaFilter;
import br.com.palota.mtmanager.domain.repository.specs.AreaSpecs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;
    private final PlantService plantService;
    private final ModelMapper mapper;

    @Transactional
    public AreaDetailsDTO save(AreaCreationDTO dto) {
        log.info(Constants.LOG_METHOD_MESSAGE, "save", "salvando entidade Area");
        areaRepository.findFirstByCodeAndPlantId(dto.getCode(), dto.getPlant().getId()).ifPresent(a ->
                {
                    throw new BusinessException(String.format("Já existe uma Área com código %s para esta Planta", dto.getCode()));
                });
        var area = mapper.map(dto, Area.class);
        var plant = plantService.findOrFail(dto.getPlant().getId());
        area.setPlant(plant);
        return mapper.map(areaRepository.save(area), AreaDetailsDTO.class);
    }

    @Transactional
    public AreaDetailsDTO update(Long id, AreaCreationDTO dto) {
        log.info(Constants.LOG_METHOD_MESSAGE, "update", "Atualizando entidade Area");
        var oldEntity = findOrFail(id);
        var plant = plantService.findOrFail(dto.getPlant().getId());
        oldEntity.setPlant(new Plant());
        mapper.map(dto, oldEntity);
        oldEntity.setPlant(plant);
        return mapper.map(oldEntity, AreaDetailsDTO.class);
    }

    public AreaDetailsDTO findById(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Buscando entidade Area por ID", id);
        return mapper.map(findOrFail(id), AreaDetailsDTO.class);
    }

    public Page<AreaSummaryDTO> findByFilter(AreaFilter filter, Pageable pageable) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_FILTER, "findByFilter", "Buscando entidades Area paginadas por filtro", filter);
        return areaRepository.findAll(AreaSpecs.withFilter(filter), pageable)
                .map(e -> mapper.map(e, AreaSummaryDTO.class));
    }

    public List<AreaSummaryDTO> findByCodeOrName(String restriction) {
        log.info(Constants.LOG_METHOD_MESSAGE, "findByCodeOrName", "Listando entidade Area por código ou nome");
        return areaRepository.findTop10ByCodeContainingIgnoreCaseOrNameContainingIgnoreCase(restriction, restriction)
                .stream().map(e -> mapper.map(e, AreaSummaryDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Deletando entiade Area", id);
        areaRepository.delete(findOrFail(id));
    }

    protected Area findOrFail(Long id) {
        return areaRepository.findById(id)
                .orElseThrow(() -> new PlantNotFoundException(String.format("Entidade Area com id %d não encontrada", id)));
    }

}
