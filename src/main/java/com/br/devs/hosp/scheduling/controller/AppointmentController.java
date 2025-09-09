package com.br.devs.hosp.scheduling.controller;

import com.br.devs.hosp.scheduling.controller.dto.AppointmentDTO;
import com.br.devs.hosp.scheduling.entities.Appointment;
import com.br.devs.hosp.scheduling.service.AppointmentService;
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

    @PostMapping
    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment createAppointment(@RequestBody AppointmentDTO appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @PutMapping("/{appointmentId}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    public Appointment updateAppointment(@PathVariable("appointmentId") String appointmentId, @RequestBody AppointmentDTO appointment) {
        return appointmentService.updateAppointment(appointmentId, appointment);
    }

    @DeleteMapping("/{appointmentId}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable("appointmentId") String appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }
}
