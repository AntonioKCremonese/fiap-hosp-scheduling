package com.br.devs.hosp.scheduling.controller.dto;

import com.br.devs.hosp.scheduling.entities.enums.AppointmentStatus;

public record AppointmentDTO(String appointmentDate, String duration, AppointmentStatus status, String observation, String patientId, String doctorId) {
}
