package com.br.devs.hosp.scheduling.entities;

import com.br.devs.hosp.scheduling.entities.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Time;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "`appointment`")
public class Appointment {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", columnDefinition = "varchar(36)")
    private String id;

    @Column(name = "date_appointment")
    private LocalDateTime dateTimeAppointment;

    @Column
    private Time duration;

    @Column
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Column
    private String observation;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;
}
