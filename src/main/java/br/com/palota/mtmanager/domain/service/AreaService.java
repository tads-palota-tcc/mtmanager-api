package br.com.palota.mtmanager.domain.service;

import br.com.palota.mtmanager.core.Constants;
import br.com.palota.mtmanager.domain.exception.PlantNotFoundException;
import br.com.palota.mtmanager.domain.model.Area;
import br.com.palota.mtmanager.domain.repository.AreaRepository;
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
public class AreaService {

    private final AreaRepository areaRepository;
    private final PlantService plantService;
    private final ModelMapper modelMapper;

    @Transactional
    public Area save(Area area) {
        log.info(Constants.LOG_METHOD_MESSAGE, "save", "salvando entidade Area");
        var plant = plantService.findById(area.getPlant().getId());
        area.setPlant(plant);
        return areaRepository.save(area);
    }

    public Area findById(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Buscando entidade Area por ID", id);
        return areaRepository.findById(id)
                .orElseThrow(() -> new PlantNotFoundException(String.format("Entidade Area com id %d não encontrada", id)));
    }

    public Page<Area> findByRestriction(String restriction, Pageable pageable) {
        log.info(Constants.LOG_METHOD_MESSAGE, "findByRestriction", "Buscando entidades Area paginadas");
        return areaRepository.findByRestriction(restriction, pageable);
    }

    @Transactional
    public void delete(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Deletando entiade Area", id);
        var entity = findById(id);
        areaRepository.delete(entity);
    }

}
