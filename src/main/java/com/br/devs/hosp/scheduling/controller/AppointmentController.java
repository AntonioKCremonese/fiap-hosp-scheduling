package com.br.devs.hosp.scheduling.controller;

import com.br.devs.hosp.scheduling.controller.dto.input.AppointmentInputDTO;
import com.br.devs.hosp.scheduling.controller.dto.output.AppointmentOutputDTO;
import com.br.devs.hosp.scheduling.entities.Appointment;
import com.br.devs.hosp.scheduling.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final RabbitTemplate rabbitTemplate;

    public AppointmentController(AppointmentService appointmentService, RabbitTemplate rabbitTemplate) {
        this.appointmentService = appointmentService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentOutputDTO>> getAppointments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "patient") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(appointmentService.getAllAppointments(pageable));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByUserId(userId));
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentOutputDTO createAppointment(@RequestBody @Valid AppointmentInputDTO appointment) {
        var appointmentCreated = appointmentService.createAppointment(appointment);
        rabbitTemplate.convertAndSend("appointmentsQueue", appointmentCreated);
        return appointmentCreated;
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @PutMapping("/{appointmentId}")
    public AppointmentOutputDTO updateAppointment(@PathVariable("appointmentId") String appointmentId, @RequestBody @Valid AppointmentInputDTO appointment) {
        var appointmentUpdated = appointmentService.updateAppointment(appointmentId, appointment);
        rabbitTemplate.convertAndSend("appointmentsQueue", appointmentUpdated);
        return appointmentUpdated;
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @DeleteMapping("/{appointmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable("appointmentId") String appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }
}