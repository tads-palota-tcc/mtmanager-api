package br.com.palota.mtmanager.api.controller;

import br.com.palota.mtmanager.api.dto.PressureIndicatorCreationDTO;
import br.com.palota.mtmanager.api.dto.PressureIndicatorDetailsDTO;
import br.com.palota.mtmanager.core.Constants;
import br.com.palota.mtmanager.domain.service.PressureIndicatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/pressure-indicators")
public class PressureIndicatorsController {

    private final PressureIndicatorService pressureIndicatorService;

    @PostMapping
    @Secured("DEVICE_CREATE")
    public ResponseEntity<PressureIndicatorDetailsDTO> create(@RequestBody @Valid PressureIndicatorCreationDTO dto, UriComponentsBuilder builder) {
        log.info(Constants.LOG_METHOD_MESSAGE, "create", "Recebendo chamada para criação de entidade PressureIndicator");
        var created = pressureIndicatorService.save(dto);
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "create", "Entidade PressureIndicator criada com sucesso", created.getId());
        var uri = builder.path("/pressure-indicators/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("{id}")
    @Secured("DEVICE_READ")
    public ResponseEntity<PressureIndicatorDetailsDTO> findById(@PathVariable Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Recebendo chamada consulta de entidade PressureIndicator por ID", id);
        return ResponseEntity.ok(pressureIndicatorService.findById(id));
    }

}
