package br.com.palota.mtmanager.domain.service;

import br.com.palota.mtmanager.api.dto.PressureIndicatorCreationDTO;
import br.com.palota.mtmanager.api.dto.PressureIndicatorDetailsDTO;
import br.com.palota.mtmanager.core.Constants;
import br.com.palota.mtmanager.domain.exception.PlantNotFoundException;
import br.com.palota.mtmanager.domain.model.PressureIndicator;
import br.com.palota.mtmanager.domain.repository.PressureIndicatorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PressureIndicatorService {

    private final PressureIndicatorRepository pressureIndicatorRepository;
    private final ModelMapper mapper;

    public PressureIndicatorDetailsDTO save(PressureIndicatorCreationDTO dto) {
        log.info(Constants.LOG_METHOD_MESSAGE, "save", "salvando entidade PressureIndicator");
        var entity = pressureIndicatorRepository.save(mapper.map(dto, PressureIndicator.class));
        return mapper.map(entity, PressureIndicatorDetailsDTO.class);
    }

    public PressureIndicatorDetailsDTO findById(Long id) {
        log.info(Constants.LOG_METHOD_MESSAGE + Constants.LOG_ENTITY_ID, "findById", "Buscando entidade Plant por ID", id);
        return mapper.map(findOrFail(id), PressureIndicatorDetailsDTO.class);
    }

    protected PressureIndicator findOrFail(Long id) {
        return pressureIndicatorRepository.findById(id)
                .orElseThrow(() -> new PlantNotFoundException(String.format("Entidade PressureIndicator com id %d não encontrada", id)));
    }
}
