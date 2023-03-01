package br.com.palota.mtmanager.domain.service;

import br.com.palota.mtmanager.api.dto.PlantCreationDTO;
import br.com.palota.mtmanager.api.dto.PlantDetailsDTO;
import br.com.palota.mtmanager.api.dto.PlantSummaryDTO;
import br.com.palota.mtmanager.core.Constants;
import br.com.palota.mtmanager.domain.exception.PlantNotFoundException;
import br.com.palota.mtmanager.domain.model.Plant;
import br.com.palota.mtmanager.domain.repository.PlantRepository;
import br.com.palota.mtmanager.domain.repository.filter.PlantFilter;
import br.com.palota.mtmanager.domain.repository.specs.PlantSpecs;
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
public class PlantService {

    private final PlantRepository plantRepository;
    private final ModelMapper mapper;

    @Transactional
    public PlantDetailsDTO save(PlantCreationDTO dto) {
        log.info(Constants.LOG_METHOD_MESSAGE, "save", "salvando entidade Plant");
        var entity = plantRepository.save(mapper.map(dto, Plant.class));
        return mapper.map(entity, PlantDetailsDTO.class);
    }

    @Transactional
    public PlantDetailsDTO update(Long id, PlantCreationDTO dto) {
        log.info(Constants.LOG_METHOD_MESSAGE, "update", "Atualizando entidade Plant");
        var oldEntity = findOrFail(id);
        mapper.map(dto, oldEntity);
        return mapper.map(oldEntity, PlantDetailsDTO.class);
    }

    public PlantDetailsDTO findById(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Buscando entidade Plant por ID", id);
        return mapper.map(findOrFail(id), PlantDetailsDTO.class);
    }

    public Page<PlantSummaryDTO> findByFilter(PlantFilter filter, Pageable pageable) {
        log.info(Constants.LOG_METHOD_MESSAGE, "findByFilter", "Buscando entidades Plant paginadas");
        return plantRepository.findAll(PlantSpecs.withFilter(filter), pageable)
                .map(e -> mapper.map(e, PlantSummaryDTO.class));
    }

    @Transactional
    public void delete(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Deletando entiade Plant", id);
        plantRepository.delete(findOrFail(id));
    }

    public List<PlantSummaryDTO> findByCodeOrName(String restriction) {
        log.info(Constants.LOG_METHOD_MESSAGE, "findByCodeOrName", "Listando entidade Plant por código ou nome");
        return plantRepository.findTop10ByCodeContainingIgnoreCaseOrNameContainingIgnoreCase(restriction, restriction)
                .stream().map(e -> mapper.map(e, PlantSummaryDTO.class)).collect(Collectors.toList());
    }

    protected Plant findOrFail(Long id) {
        return plantRepository.findById(id)
                .orElseThrow(() -> new PlantNotFoundException(String.format("Entidade Plant com id %d não encontrada", id)));
    }

}
