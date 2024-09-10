package com.agrosupport.api.appointment.domain.services;

import com.agrosupport.api.appointment.domain.model.aggregates.Appointment;
import com.agrosupport.api.appointment.domain.model.commands.CreateAppointmentCommand;
import com.agrosupport.api.appointment.domain.model.commands.DeleteAppointmentCommand;
import com.agrosupport.api.appointment.domain.model.commands.UpdateAppointmentCommand;

import java.util.Optional;

public interface AppointmentCommandService {
    Long handle(CreateAppointmentCommand command);
    Optional<Appointment> handle(UpdateAppointmentCommand command);
    void handle(DeleteAppointmentCommand command);
}
