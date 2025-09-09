package com.br.devs.hosp.scheduling.entities;

import com.br.devs.hosp.scheduling.entities.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", columnDefinition = "varchar(36)")
    private String id;

    @Column
    private String name;

    @Column
    private String mail;

    @Enumerated(EnumType.STRING)
    @Column
    private UserType userType;

    @Column
    private String expertise;
}
