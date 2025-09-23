package com.br.devs.hosp.scheduling.service;

import com.br.devs.hosp.scheduling.controller.dto.input.AppointmentInputDTO;
import com.br.devs.hosp.scheduling.controller.dto.output.AppointmentOutputDTO;
import com.br.devs.hosp.scheduling.entities.Appointment;
import com.br.devs.hosp.scheduling.entities.enums.UserType;
import com.br.devs.hosp.scheduling.mapper.AppointmentMapper;
import com.br.devs.hosp.scheduling.repository.AppointmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;
    private final AppointmentMapper appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, UserService userService, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.userService = userService;
        this.appointmentMapper = appointmentMapper;
    }

    @Transactional(readOnly = true)
    public Page<AppointmentOutputDTO> getAllAppointments(Pageable pageable) {
        Page<Appointment> appointments = appointmentRepository.findAll(pageable);
        return appointments.map(appointmentMapper::toDto);
    }

    @Transactional(readOnly = true)
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

    @Transactional
    public AppointmentOutputDTO createAppointment(AppointmentInputDTO appointment) {
        Appointment newAppointment = appointmentMapper.toEntity(appointment);
        newAppointment.setPatient(userService.findUserById(appointment.getPatientId()));
        newAppointment.setDoctor(userService.findUserById(appointment.getDoctorId()));
        return appointmentMapper.toDto(appointmentRepository.save(newAppointment));
    }

    @Transactional
    public AppointmentOutputDTO updateAppointment(String appointmentId, AppointmentInputDTO appointment) {
        Appointment existingAppointment = findAppointmentById(appointmentId);
        appointmentMapper.copyProperties(appointment, existingAppointment);
        existingAppointment.setPatient(userService.findUserById(appointment.getPatientId()));
        existingAppointment.setDoctor(userService.findUserById(appointment.getDoctorId()));
        return appointmentMapper.toDto(appointmentRepository.save(existingAppointment));
    }

    @Transactional
    public void deleteAppointment(String appointmentId) {
        appointmentRepository.delete(findAppointmentById(appointmentId));
    }

    protected Appointment findAppointmentById(String appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }
}