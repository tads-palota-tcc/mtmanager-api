package br.com.palota.mtmanager.api.controller;

import br.com.palota.mtmanager.api.assembler.AreaAssembler;
import br.com.palota.mtmanager.api.dto.AreaCreationDTO;
import br.com.palota.mtmanager.api.dto.AreaDetailsDTO;
import br.com.palota.mtmanager.api.dto.AreaSummaryDTO;
import br.com.palota.mtmanager.core.Constants;
import br.com.palota.mtmanager.domain.exception.BusinessException;
import br.com.palota.mtmanager.domain.exception.EntityNotFoundException;
import br.com.palota.mtmanager.domain.repository.filter.AreaFilter;
import br.com.palota.mtmanager.domain.service.AreaService;
import br.com.palota.mtmanager.domain.service.PlantService;
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
@RequestMapping("/areas")
public class AreaController {

    private final AreaService areaService;
    private final AreaAssembler areaAssembler;
    private final PlantService plantService;

    @PostMapping
    public ResponseEntity<AreaDetailsDTO> create(@RequestBody @Valid AreaCreationDTO dto, UriComponentsBuilder builder) {
        log.info(Constants.LOG_METHOD_MESSAGE, "create", "Recebendo chamada para criação de entidade Area");
        try {
            var created = areaService.save(areaAssembler.toEntity(dto));
            log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "create", "Entidade Area criada com sucesso", created.getId());
            var uri = builder.path("/areas/{id}").buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uri).body(areaAssembler.toDetailResponse(created));
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<AreaDetailsDTO> update(@PathVariable Long id, @RequestBody @Valid AreaCreationDTO dto) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "update", "Recebendo chamada para atualização de entidade Area", id);
        var entity = areaService.findById(id);
        areaAssembler.copyToEntity(dto, entity);
        try {
            entity = areaService.save(entity);
            log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "update", "Entidade Area atualizada com sucesso", id);
            return ResponseEntity.ok(areaAssembler.toDetailResponse(entity));
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AreaDetailsDTO> findById(@PathVariable Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Recebendo chamada consulta de entidade Area por ID", id);
        var entity = areaService.findById(id);
        return ResponseEntity.ok(areaAssembler.toDetailResponse(entity));
    }

    @GetMapping
    public ResponseEntity<Page<AreaSummaryDTO>> findByRestriction(AreaFilter filter, Pageable pageable) {
        log.info(Constants.LOG_METHOD_MESSAGE, "findByRestriction", "Recebendo chamada para listagem de entidades Area");
        var entities = areaService.findByFilter(filter, pageable);
        return ResponseEntity.ok(areaAssembler.toSummaryPage(entities));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Recebendo chamada para deletar entidade Area", id);
        areaService.delete(id);
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Entidade Area deletada com sucesso", id);
        return ResponseEntity.noContent().build();
    }

}
