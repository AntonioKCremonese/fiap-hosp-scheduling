package com.br.devs.hosp.scheduling.controller.graphql;

import com.br.devs.hosp.scheduling.controller.dto.input.AppointmentInputDTO;
import com.br.devs.hosp.scheduling.controller.dto.output.AppointmentOutputDTO;
import com.br.devs.hosp.scheduling.entities.Appointment;
import com.br.devs.hosp.scheduling.service.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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
    public Page<AppointmentOutputDTO> getAppointments(Pageable pageable) {
        return appointmentService.getAllAppointments(pageable);
    }

    @QueryMapping
    public List<Appointment> getAppointmentsByUserId(@Argument String userId) {
        return appointmentService.getAppointmentsByUserId(userId);
    }

    @MutationMapping
    public AppointmentOutputDTO createAppointment(@Argument AppointmentInputDTO appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @MutationMapping
    public AppointmentOutputDTO updateAppointment(@Argument String appointmentId, @Argument AppointmentInputDTO appointment) {
        return appointmentService.updateAppointment(appointmentId, appointment);
    }

    @MutationMapping
    public void deleteAppointment(@Argument String appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }
}