package com.br.devs.hosp.scheduling.controller.graphql;

import com.br.devs.hosp.scheduling.controller.dto.AppointmentDTO;
import com.br.devs.hosp.scheduling.entities.Appointment;
import com.br.devs.hosp.scheduling.service.AppointmentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppointmentGraphQLController {

    private final AppointmentService appointmentService;

    public AppointmentGraphQLController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @QueryMapping
    public List<Appointment> getAppointments() {
        return appointmentService.getAllAppointments();
    }

    @QueryMapping
    public List<Appointment> getAppointmentsByUserId(@Argument String userId) {
        return appointmentService.getAppointmentsByUserId(userId);
    }

    @MutationMapping
    public Appointment createAppointment(@Argument AppointmentDTO appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @MutationMapping
    public Appointment updateAppointment(@Argument String appointmentId, @Argument AppointmentDTO appointment) {
        return appointmentService.updateAppointment(appointmentId, appointment);
    }

    @MutationMapping
    public void deleteAppointment(@Argument String appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }
}
