package com.br.devs.hosp.scheduling.controller.dto.output;

import com.br.devs.hosp.scheduling.entities.enums.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentOutputDTO {
    private String id;
    private LocalDateTime dateTimeAppointment;
    private Time duration;
    private AppointmentStatus status;
    private String observation;
    private UserOutputDTO patient;
    private UserOutputDTO doctor;
}