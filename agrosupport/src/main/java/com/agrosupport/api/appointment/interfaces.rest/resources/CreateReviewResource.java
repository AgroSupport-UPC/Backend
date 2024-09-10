package com.agrosupport.api.appointment.interfaces.rest.resources;

public record CreateReviewResource(Long advisorId,
                                   String comment,
                                   Integer rating) {
}
