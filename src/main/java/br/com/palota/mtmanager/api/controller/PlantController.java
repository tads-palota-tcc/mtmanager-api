package br.com.palota.mtmanager.api.controller;

import br.com.palota.mtmanager.api.assembler.PlantAssembler;
import br.com.palota.mtmanager.api.dto.PlantCreationDTO;
import br.com.palota.mtmanager.api.dto.PlantDetailsDTO;
import br.com.palota.mtmanager.core.Constants;
import br.com.palota.mtmanager.domain.service.PlantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/plants")
public class PlantController {

    private final PlantAssembler plantAssembler;
    private final PlantService plantService;

    @PostMapping
    public ResponseEntity<PlantDetailsDTO> create(@RequestBody @Valid PlantCreationDTO dto, UriComponentsBuilder builder) {
        log.info(Constants.LOG_METHOD_MESSAGE, "create", "Recebendo chamada para criação de entidade Plant");
        var created = plantService.create(plantAssembler.fromDTO(dto));
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "create", "Entidade Plant criada com sucesso", created.getId());
        var uri = builder.path("/plants/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(plantAssembler.fromEntity(created));
    }

    @GetMapping("{id}")
    public ResponseEntity<PlantDetailsDTO> findById(@PathVariable Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Recebendo chamada consulta de entidade Plant por ID", id);
        var entity = plantService.findById(id);
        return ResponseEntity.ok(plantAssembler.fromEntity(entity));
    }

}
