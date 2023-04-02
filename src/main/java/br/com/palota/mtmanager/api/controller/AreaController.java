package br.com.palota.mtmanager.api.controller;

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
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/areas")
public class AreaController {

    private final AreaService areaService;
    private final PlantService plantService;

    @PostMapping
    @Secured("AREA_CREATE")
    public ResponseEntity<AreaDetailsDTO> create(@RequestBody @Valid AreaCreationDTO dto, UriComponentsBuilder builder) {
        log.info(Constants.LOG_METHOD_MESSAGE, "create", "Recebendo chamada para criação de entidade Area");
        try {
            var created = areaService.save(dto);
            log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "create", "Entidade Area criada com sucesso", created.getId());
            var uri = builder.path("/areas/{id}").buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uri).body(created);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("{id}")
    @Secured("AREA_UPDATE")
    public ResponseEntity<AreaDetailsDTO> update(@PathVariable Long id, @RequestBody @Valid AreaCreationDTO dto) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "update", "Recebendo chamada para atualização de entidade Area", id);
        try {
            var updated = areaService.update(id, dto);
            log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "update", "Entidade Area atualizada com sucesso", id);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @GetMapping("{id}")
    @Secured("AREA_READ")
    public ResponseEntity<AreaDetailsDTO> findById(@PathVariable Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Recebendo chamada consulta de entidade Area por ID", id);
        return ResponseEntity.ok(areaService.findById(id));
    }

    @GetMapping
    @Secured("AREA_READ")
    public ResponseEntity<Page<AreaSummaryDTO>> findByRestriction(AreaFilter filter, Pageable pageable) {
        log.info(Constants.LOG_METHOD_MESSAGE, "findByRestriction", "Recebendo chamada para listagem de entidades Area");
        return ResponseEntity.ok(areaService.findByFilter(filter, pageable));
    }

    @DeleteMapping("{id}")
    @Secured("AREA_REMOVE")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Recebendo chamada para deletar entidade Area", id);
        areaService.delete(id);
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Entidade Area deletada com sucesso", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(params = "restriction")
    @Secured("AREA_READ")
    public ResponseEntity<List<AreaSummaryDTO>> findByRestriction(@RequestParam String restriction) {
        log.info(Constants.LOG_METHOD_MESSAGE, "findByRestriction", "Recebendo chamada para listagem de entidades Plant");
        return ResponseEntity.ok(areaService.findByCodeOrName(restriction));
    }

}
