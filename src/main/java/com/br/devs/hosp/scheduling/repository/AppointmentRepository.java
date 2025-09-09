package com.br.devs.hosp.scheduling.repository;

import com.br.devs.hosp.scheduling.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
}
