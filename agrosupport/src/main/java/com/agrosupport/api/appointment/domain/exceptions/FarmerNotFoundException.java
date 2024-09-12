package com.agrosupport.api.appointment.domain.exceptions;

public class FarmerNotFoundException extends RuntimeException {
    public FarmerNotFoundException(Long farmerId) {
        super("Farmer with id " + farmerId + " not found");
    }
}
