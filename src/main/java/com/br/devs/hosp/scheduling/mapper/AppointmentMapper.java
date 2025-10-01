package com.br.devs.hosp.scheduling.mapper;

import com.br.devs.hosp.scheduling.controller.dto.input.AppointmentInputDTO;
import com.br.devs.hosp.scheduling.controller.dto.output.AppointmentOutputDTO;
import com.br.devs.hosp.scheduling.entities.Appointment;
import com.br.devs.hosp.scheduling.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    private final ModelMapper modelMapper;

    public AppointmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AppointmentOutputDTO toDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentOutputDTO.class);
    }

    public Appointment toEntity(AppointmentInputDTO appointmentInputDTO) {
        return modelMapper.map(appointmentInputDTO, Appointment.class);
    }

    public void copyProperties(AppointmentInputDTO appointmentInputDTO, Appointment appointment) {
        modelMapper.map(appointmentInputDTO, appointment);
    }
}