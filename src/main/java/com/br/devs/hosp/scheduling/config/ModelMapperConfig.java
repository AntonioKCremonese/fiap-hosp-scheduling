package com.br.devs.hosp.scheduling.config;

import com.br.devs.hosp.scheduling.controller.dto.input.AppointmentInputDTO;
import com.br.devs.hosp.scheduling.entities.Appointment;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<AppointmentInputDTO, Appointment> appointmentTypeMap = modelMapper.createTypeMap(AppointmentInputDTO.class, Appointment.class);
        appointmentTypeMap.addMappings(mapper -> {
            mapper.skip(Appointment::setPatient);
            mapper.skip(Appointment::setDoctor);
        });

        return modelMapper;
    }
}