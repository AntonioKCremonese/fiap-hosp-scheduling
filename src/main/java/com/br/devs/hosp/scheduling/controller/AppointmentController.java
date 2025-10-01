package com.br.devs.hosp.scheduling.controller;

import com.br.devs.hosp.scheduling.controller.dto.input.AppointmentInputDTO;
import com.br.devs.hosp.scheduling.controller.dto.output.AppointmentOutputDTO;
import com.br.devs.hosp.scheduling.entities.Appointment;
import com.br.devs.hosp.scheduling.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByUserId(userId));
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentOutputDTO createAppointment(@RequestBody @Valid AppointmentInputDTO appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @PutMapping("/{appointmentId}")
    public AppointmentOutputDTO updateAppointment(@PathVariable("appointmentId") String appointmentId, @RequestBody @Valid AppointmentInputDTO appointment) {
        return appointmentService.updateAppointment(appointmentId, appointment);
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @DeleteMapping("/{appointmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable("appointmentId") String appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }
}