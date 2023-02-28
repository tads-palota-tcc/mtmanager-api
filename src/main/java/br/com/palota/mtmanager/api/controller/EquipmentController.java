package br.com.palota.mtmanager.api.controller;

import br.com.palota.mtmanager.api.dto.EquipmentCreationDTO;
import br.com.palota.mtmanager.api.dto.EquipmentDetailsDTO;
import br.com.palota.mtmanager.api.dto.EquipmentSummaryDTO;
import br.com.palota.mtmanager.core.Constants;
import br.com.palota.mtmanager.domain.exception.BusinessException;
import br.com.palota.mtmanager.domain.exception.EntityNotFoundException;
import br.com.palota.mtmanager.domain.repository.filter.EquipmentFilter;
import br.com.palota.mtmanager.domain.service.AreaService;
import br.com.palota.mtmanager.domain.service.EquipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/equipments")
public class EquipmentController {

    private final EquipmentService equipmentService;
    private final AreaService areaService;

    @PostMapping
    public ResponseEntity<EquipmentDetailsDTO> create(@RequestBody @Valid EquipmentCreationDTO dto, UriComponentsBuilder builder) {
        log.info(Constants.LOG_METHOD_MESSAGE, "create", "Recebendo chamada para criação de entidade Equipment");
        try {
            var created = equipmentService.save(dto);
            log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "create", "Entidade Equipment criada com sucesso", created.getId());
            var uri = builder.path("/equipments/{id}").buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uri).body(created);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<EquipmentDetailsDTO> update(@PathVariable Long id, @RequestBody @Valid EquipmentCreationDTO dto) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "update", "Recebendo chamada para atualização de entidade Equipment", id);
        try {
            var updated = equipmentService.update(id, dto);
            log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "update", "Entidade Equipment atualizada com sucesso", id);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<EquipmentDetailsDTO> findById(@PathVariable Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Recebendo chamada consulta de entidade Equipment por ID", id);
        return ResponseEntity.ok(equipmentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<EquipmentSummaryDTO>> findByFilter(EquipmentFilter filter, Pageable pageable) {
        log.info(Constants.LOG_METHOD_MESSAGE, "findByFilter", "Recebendo chamada para listagem de entidades Equipment paginadas por filtro");
        return ResponseEntity.ok(equipmentService.findByFilter(filter, pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Recebendo chamada para deletar entidade Equipment", id);
        equipmentService.delete(id);
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Entidade Equipment deletada com sucesso", id);
        return ResponseEntity.noContent().build();
    }

}
