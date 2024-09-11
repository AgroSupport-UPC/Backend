package com.agrosupport.api.appointment.domain.model.commands;

import java.time.LocalDate;

public record UpdateAppointmentCommand(Long id,
                                       String message,
                                       String status,
                                       LocalDate scheduledDate,
                                       String startTime,
                                       String endTime) {
}
