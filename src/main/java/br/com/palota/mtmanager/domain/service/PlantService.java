package br.com.palota.mtmanager.domain.service;

import br.com.palota.mtmanager.core.Constants;
import br.com.palota.mtmanager.domain.exception.PlantNotFoundException;
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
    private final ModelMapper mapper;

    @Transactional
    public Plant save(Plant plant) {
        log.info(Constants.LOG_METHOD_MESSAGE, "create", "salvando entidade Plant");
        var entity = plantRepository.save(plant);
        return entity;
    }

    public Plant findById(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Buscando entidade Plant por ID", id);
        return plantRepository.findById(id)
                .orElseThrow(() -> new PlantNotFoundException(String.format("Entidade Plant com id %d não encontrada", id)));
    }

    public Page<Plant> findByRestriction(String restriction, Pageable pageable) {
        log.info(Constants.LOG_METHOD_MESSAGE, "findByRestriction", "Buscando entidades Plant paginadas");
        return plantRepository.findByRestriction(restriction, pageable);
    }

    @Transactional
    public void delete(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Deletando entiade Plant", id);
        var entity = findById(id);
        plantRepository.delete(entity);
    }

}
