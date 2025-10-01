package com.br.devs.hosp.scheduling.entities;

import com.br.devs.hosp.scheduling.entities.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;

@Data
@Entity
@Table(name = "`user`")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", columnDefinition = "varchar(36)")
    private String id;

    @Column
    private String name;

    @Column(name = "email")
    private String email;

    @Column
    private String login;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private UserType userType;

    @Column
    private String expertise;
}