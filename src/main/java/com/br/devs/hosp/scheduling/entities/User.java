package com.br.devs.hosp.scheduling.entities;

import com.br.devs.hosp.scheduling.entities.enums.UserType;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
    private UUID id;
    private String name;
    private String mail;
    private UserType userType;
    private String expertise;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalAppointment> appointments;
}
