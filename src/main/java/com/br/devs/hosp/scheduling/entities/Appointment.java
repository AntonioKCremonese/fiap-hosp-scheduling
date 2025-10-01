package com.br.devs.hosp.scheduling.entities;

import com.br.devs.hosp.scheduling.entities.enums.AppointmentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Time;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", columnDefinition = "varchar(36)")
    private String id;

    @Column(name = "date_appointment")
    private LocalDateTime dateTimeAppointment;

    @Column(name = "duration")
    private Time duration;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Column(name = "observation")
    private String observation;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;
}