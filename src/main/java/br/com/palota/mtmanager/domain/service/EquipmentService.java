package br.com.palota.mtmanager.domain.service;

import br.com.palota.mtmanager.core.Constants;
import br.com.palota.mtmanager.domain.exception.BusinessException;
import br.com.palota.mtmanager.domain.exception.PlantNotFoundException;
import br.com.palota.mtmanager.domain.model.Equipment;
import br.com.palota.mtmanager.domain.repository.EquipmentRepository;
import br.com.palota.mtmanager.domain.repository.filter.EquipmentFilter;
import br.com.palota.mtmanager.domain.repository.specs.EquipmentSpecs;
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
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final AreaService areaService;
    private final ModelMapper modelMapper;

    @Transactional
    public Equipment save(Equipment equipment) {
        log.info(Constants.LOG_METHOD_MESSAGE, "save", "salvando entidade Equipment");
        equipmentRepository.findFirstByTagAndAreaId(equipment.getTag(), equipment.getArea().getId()).ifPresent(a ->
                {
                    throw new BusinessException(String.format("Já existe um Equipamento com a Tag %s para esta Área", equipment.getTag()));
                });
        var area = areaService.findById(equipment.getArea().getId());
        equipment.setArea(area);
        return equipmentRepository.save(equipment);
    }

    public Equipment findById(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Buscando entidade Equipment por ID", id);
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new PlantNotFoundException(String.format("Entidade Equipment com id %d não encontrada", id)));
    }

    public Page<Equipment> findByFilter(EquipmentFilter filter, Pageable pageable) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_FILTER, "findByFilter", "Buscando entidades Equipment paginadas por filtro", filter);
        return equipmentRepository.findAll(EquipmentSpecs.withFilter(filter), pageable);
    }

    @Transactional
    public void delete(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Deletando entiade Equipment", id);
        var entity = findById(id);
        equipmentRepository.delete(entity);
    }

}
