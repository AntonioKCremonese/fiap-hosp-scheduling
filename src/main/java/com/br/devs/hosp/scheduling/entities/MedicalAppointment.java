package com.br.devs.hosp.scheduling.entities;

import com.br.devs.hosp.scheduling.entities.enums.AppointmentStatus;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "medical_appointment")
public class MedicalAppointment {
    private UUID id;

    private LocalDateTime dateTimeAppointment;

    private Time duration;

    private AppointmentStatus status;

    private String observation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User doctor;
}
