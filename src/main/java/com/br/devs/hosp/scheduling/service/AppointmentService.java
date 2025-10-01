package com.br.devs.hosp.scheduling.service;

import com.br.devs.hosp.scheduling.controller.dto.NotificationRequest;
import com.br.devs.hosp.scheduling.controller.dto.input.AppointmentInputDTO;
import com.br.devs.hosp.scheduling.controller.dto.output.AppointmentOutputDTO;
import com.br.devs.hosp.scheduling.entities.Appointment;
import com.br.devs.hosp.scheduling.entities.enums.UserType;
import com.br.devs.hosp.scheduling.mapper.AppointmentMapper;
import com.br.devs.hosp.scheduling.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;
    private final AppointmentMapper appointmentMapper;
    private final NotificationService notificationService;

    public AppointmentService(AppointmentRepository appointmentRepository, UserService userService,
                              AppointmentMapper appointmentMapper, NotificationService notificationService) {
        this.appointmentRepository = appointmentRepository;
        this.userService = userService;
        this.appointmentMapper = appointmentMapper;
        this.notificationService = notificationService;
    }

    @Transactional(readOnly = true)
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
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
        newAppointment = appointmentRepository.save(newAppointment);

        String message = String.format("Sua consulta com o(a) médico(a) %s foi agendada para o dia %s às %s horas.",
                newAppointment.getDoctor().getName(), "{date}", "{time}");
        sendNotification(newAppointment, "Consulta Agendada", message);
        return appointmentMapper.toDto(newAppointment);
    }

    @Transactional
    public AppointmentOutputDTO updateAppointment(String appointmentId, AppointmentInputDTO appointment) {
        Appointment existingAppointment = findAppointmentById(appointmentId);
        appointmentMapper.copyProperties(appointment, existingAppointment);
        existingAppointment.setPatient(userService.findUserById(appointment.getPatientId()));
        existingAppointment.setDoctor(userService.findUserById(appointment.getDoctorId()));
        existingAppointment = appointmentRepository.save(existingAppointment);

        String message = String.format("O dia e horário da sua consulta com o(a) médico(a) %s foi atualizado para o dia %s às %s horas.",
                existingAppointment.getDoctor().getName(), "{date}", "{time}");
        sendNotification(existingAppointment, "Consulta Atualizada", message);

        return appointmentMapper.toDto(existingAppointment);
    }

    @Transactional
    public void deleteAppointment(String appointmentId) {
        appointmentRepository.delete(findAppointmentById(appointmentId));
    }

    public List<Appointment> futureAppointmentsByUserId(String userId) {
        var now = LocalDateTime.now();
        var user = userService.getUserById(userId);
        if (user.getUserType().equals(UserType.PATIENT)) {
            return appointmentRepository.findAll().stream()
                    .filter(appointment -> appointment.getPatient().getId().equals(userId))
                    .filter(appointment -> appointment.getDateTimeAppointment().isAfter(now))
                    .toList();
        }
        return appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getDoctor().getId().equals(userId))
                .filter(appointment -> appointment.getDateTimeAppointment().isAfter(now))
                .toList();
    }

    protected Appointment findAppointmentById(String appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    private void sendNotification(Appointment appointment, String title, String messageTemplate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedDate = appointment.getDateTimeAppointment().format(dateFormatter);
        String formattedTime = appointment.getDateTimeAppointment().format(timeFormatter);
        String finalMessage = messageTemplate.replace("{date}", formattedDate).replace("{time}", formattedTime);

        NotificationRequest notification = NotificationRequest.builder()
                .title(title)
                .message(finalMessage)
                .userEmail(appointment.getPatient().getEmail())
                .userId(appointment.getPatient().getId())
                .build();

        notificationService.sendNotification(notification);
    }
}