package com.agrosupport.api.appointment.domain.exceptions;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(Long id) {
        super("Appointment with id " + id + " not found");
    }
}
