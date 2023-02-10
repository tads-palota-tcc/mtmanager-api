package br.com.palota.mtmanager.domain.service;

import br.com.palota.mtmanager.core.Constants;
import br.com.palota.mtmanager.domain.exception.EntityNotFoundException;
import br.com.palota.mtmanager.domain.model.Plant;
import br.com.palota.mtmanager.domain.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepository plantRepository;

    @Transactional
    public Plant create(Plant plant) {
        log.info(Constants.LOG_METHOD_MESSAGE, "create", "salvando entidade Plant");
        return plantRepository.save(plant);
    }

    public Plant findById(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Buscando entidade Plant por ID", id);
        return plantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entidade Plant com id %d não encontrada", id)));
    }

}
