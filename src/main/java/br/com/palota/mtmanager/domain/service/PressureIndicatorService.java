package br.com.palota.mtmanager.domain.service;

import br.com.palota.mtmanager.api.dto.PressureIndicatorCreationDTO;
import br.com.palota.mtmanager.api.dto.PressureIndicatorDetailsDTO;
import br.com.palota.mtmanager.core.Constants;
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
}
