package com.br.devs.hosp.scheduling.controller.dto.input;

import com.br.devs.hosp.scheduling.entities.enums.AppointmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentInputDTO {

    @NotNull(message = "A data e hora da consulta são obrigatórias")
    private LocalDateTime dateTimeAppointment;

    @NotNull(message = "A duração é obrigatória")
    private Time duration;

    @NotNull(message = "O status é obrigatório")
    private AppointmentStatus status;

    @Size(max = 125, message = "O campo de observações deve ter no máximo 255 caracteres")
    @NotBlank(message = "O campo de observações é obrigatório")
    private String observation;

    @NotNull(message = "O id do paciente é obrigatório")
    private String patientId;

    @NotNull(message = "O id do médico é obrigatório")
    private String doctorId;
}