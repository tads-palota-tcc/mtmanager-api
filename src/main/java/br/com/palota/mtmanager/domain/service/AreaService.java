package br.com.palota.mtmanager.domain.service;

import br.com.palota.mtmanager.core.Constants;
import br.com.palota.mtmanager.domain.exception.BusinessException;
import br.com.palota.mtmanager.domain.exception.PlantNotFoundException;
import br.com.palota.mtmanager.domain.model.Area;
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
        areaRepository.findFirstByCodeAndPlantId(area.getCode(), area.getPlant().getId()).ifPresent(a ->
                {
                    throw new BusinessException(String.format("Já existe uma Área com código %s para esta Planta", area.getCode()));
                });
        var plant = plantService.findById(area.getPlant().getId());
        area.setPlant(plant);
        return areaRepository.save(area);
    }

    public Area findById(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Buscando entidade Area por ID", id);
        return areaRepository.findById(id)
                .orElseThrow(() -> new PlantNotFoundException(String.format("Entidade Area com id %d não encontrada", id)));
    }

    public Page<Area> findByFilter(AreaFilter filter, Pageable pageable) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_FILTER, "findByFilter", "Buscando entidades Area paginadas por filtro", filter);
        return areaRepository.findAll(AreaSpecs.withFilter(filter), pageable);
    }

    @Transactional
    public void delete(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Deletando entiade Area", id);
        var entity = findById(id);
        areaRepository.delete(entity);
    }

}
