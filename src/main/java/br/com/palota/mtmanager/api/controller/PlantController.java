package br.com.palota.mtmanager.api.controller;

import br.com.palota.mtmanager.api.assembler.PlantAssembler;
import br.com.palota.mtmanager.api.dto.PlantCreationDTO;
import br.com.palota.mtmanager.api.dto.PlantDetailsDTO;
import br.com.palota.mtmanager.api.dto.PlantSummaryDTO;
import br.com.palota.mtmanager.core.Constants;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/plants")
public class PlantController {

    private final PlantService plantService;
    private final PlantAssembler plantAssembler;

    @PostMapping
    public ResponseEntity<PlantDetailsDTO> create(@RequestBody @Valid PlantCreationDTO dto, UriComponentsBuilder builder) {
        log.info(Constants.LOG_METHOD_MESSAGE, "create", "Recebendo chamada para criação de entidade Plant");
        var created = plantService.save(plantAssembler.toEntity(dto));
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "create", "Entidade Plant criada com sucesso", created.getId());
        var uri = builder.path("/plants/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(plantAssembler.toDetailResponse(created));
    }

    @PutMapping("{id}")
    public ResponseEntity<PlantDetailsDTO> update(@PathVariable Long id, @RequestBody @Valid PlantCreationDTO dto) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "update", "Recebendo chamada para atualização de entidade Plant", id);
        var entity = plantService.findById(id);
        plantAssembler.copyToEntity(dto, entity);
        entity = plantService.save(entity);
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "update", "Entidade Plant atualizada com sucesso", id);
        return ResponseEntity.ok(plantAssembler.toDetailResponse(entity));
    }

    @GetMapping("{id}")
    public ResponseEntity<PlantDetailsDTO> findById(@PathVariable Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Recebendo chamada consulta de entidade Plant por ID", id);
        return ResponseEntity.ok(plantAssembler.toDetailResponse(plantService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<PlantSummaryDTO>> findByRestriction(@RequestParam(required = false) String restriction, Pageable pageable) {
        log.info(Constants.LOG_METHOD_MESSAGE, "findByRestriction", "Recebendo chamada para listagem de entidades Plant");
        var entities = plantService.findByRestriction(restriction, pageable);
        return ResponseEntity.ok(plantAssembler.toSummaryPage(entities));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Recebendo chamada para deletar entidade Plant", id);
        plantService.delete(id);
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "delete", "Entidade Plant deletada com sucesso", id);
        return ResponseEntity.noContent().build();
    }

}
