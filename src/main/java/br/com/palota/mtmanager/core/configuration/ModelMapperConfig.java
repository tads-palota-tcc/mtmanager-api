package br.com.palota.mtmanager.core.configuration;

import br.com.palota.mtmanager.domain.model.Plant;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

//        modelMapper.createTypeMap(Plant.class, Plant.class)
//                .addMappings(mapper -> mapper.skip(Plant::setId));

        return modelMapper;
    }
}
