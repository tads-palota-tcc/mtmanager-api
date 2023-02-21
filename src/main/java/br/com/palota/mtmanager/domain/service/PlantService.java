package br.com.palota.mtmanager.domain.service;

import br.com.palota.mtmanager.api.dto.PlantCreationDTO;
import br.com.palota.mtmanager.api.dto.PlantDetailsDTO;
import br.com.palota.mtmanager.api.dto.PlantSummaryDTO;
import br.com.palota.mtmanager.core.Constants;
import br.com.palota.mtmanager.domain.exception.EntityNotFoundException;
import br.com.palota.mtmanager.domain.model.Plant;
import br.com.palota.mtmanager.domain.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepository plantRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PlantDetailsDTO create(PlantCreationDTO dto) {
        log.info(Constants.LOG_METHOD_MESSAGE, "create", "salvando entidade Plant");
        var entity = plantRepository.save(modelMapper.map(dto, Plant.class));
        return modelMapper.map(entity, PlantDetailsDTO.class);
    }

    public PlantDetailsDTO findById(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Buscando entidade Plant por ID", id);
        var entity = findEntityById(id);
        return modelMapper.map(entity, PlantDetailsDTO.class);
    }

    public Page<PlantSummaryDTO> findByRestriction(String restriction, Pageable pageable) {
        log.info(Constants.LOG_METHOD_MESSAGE, "findByRestriction", "Buscando entidades Plant paginadas");
        return plantRepository.findByRestriction(restriction, pageable)
                .map(entity -> modelMapper.map(entity, PlantSummaryDTO.class));
    }

    @Transactional
    public void delete(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Deletando entiade Plant", id);
        var entity = findEntityById(id);
        plantRepository.delete(entity);
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Entiade Plant deletada com sucesso", id);
    }

    @Transactional
    public PlantDetailsDTO update(Long id, PlantCreationDTO dto) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "update", "Atualização entiade Plant", id);
        var oldEntity = findEntityById(id);
        modelMapper.map(dto, oldEntity);
        var response = modelMapper.map(oldEntity, PlantDetailsDTO.class);
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY, "update", "Entiade Plant atualizada com sucesso", response);
        return response;
    }

    private Plant findEntityById(Long id) {
        return plantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entidade Plant com id %d não encontrada", id)));
    }

}
