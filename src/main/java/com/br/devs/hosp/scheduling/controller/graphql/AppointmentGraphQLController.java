package com.br.devs.hosp.scheduling.controller.graphql;

import com.br.devs.hosp.scheduling.entities.Appointment;
import com.br.devs.hosp.scheduling.service.AppointmentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AppointmentGraphQLController {

    private final AppointmentService appointmentService;

    public AppointmentGraphQLController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @QueryMapping
    public List<Appointment> appointments() {
        return appointmentService.getAllAppointments();
    }

    @QueryMapping
    public List<Appointment> appointmentsByUserId(@Argument String userId) {
        return appointmentService.getAppointmentsByUserId(userId);
    }

    @QueryMapping
    public List<Appointment> futureAppointmentsByUserId(@Argument String userId) {
        return appointmentService.futureAppointmentsByUserId(userId);
    }
}