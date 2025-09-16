package com.br.devs.hosp.scheduling.service;

import com.br.devs.hosp.scheduling.controller.dto.input.AppointmentDTO;
import com.br.devs.hosp.scheduling.entities.Appointment;
import com.br.devs.hosp.scheduling.entities.enums.UserType;
import com.br.devs.hosp.scheduling.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;

    public AppointmentService(AppointmentRepository appointmentRepository, UserService userService) {
        this.appointmentRepository = appointmentRepository;
        this.userService = userService;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByUserId(String userId) {
        var user = userService.getUserById(userId);
        if (user.getUserType().equals(UserType.PATIENT)) {
            return appointmentRepository.findAll().stream()
                    .filter(appointment -> appointment.getPatient().getId().equals(userId))
                    .toList();
        }
        return appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getDoctor().getId().equals(userId))
                .toList();
    }

    public Appointment createAppointment(AppointmentDTO appointment) {
        var patient = userService.findUserById(appointment.patientId());
        var doctor = userService.findUserById(appointment.doctorId());

        Appointment newAppointment = new Appointment();
        newAppointment.setDateTimeAppointment(LocalDateTime.parse(appointment.appointmentDate()));
        newAppointment.setDuration(Time.valueOf(appointment.duration()));
        newAppointment.setStatus(appointment.status());
        newAppointment.setObservation(appointment.observation());
        newAppointment.setPatient(patient);
        newAppointment.setDoctor(doctor);
        return appointmentRepository.save(newAppointment);
    }

    public Appointment updateAppointment(String appointmentId, AppointmentDTO appointment) {
        var existingAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        existingAppointment.setDateTimeAppointment(LocalDateTime.parse(appointment.appointmentDate()));
        existingAppointment.setDuration(Time.valueOf(appointment.duration()));
        existingAppointment.setStatus(appointment.status());
        existingAppointment.setObservation(appointment.observation());
        return appointmentRepository.save(existingAppointment);
    }

    public void deleteAppointment(String appointmentId) {
        var existingAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        appointmentRepository.delete(existingAppointment);
    }
}