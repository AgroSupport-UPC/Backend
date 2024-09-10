package com.agrosupport.api.appointment.domain.model.commands;

public record CreateReviewCommand(Long advisorId,
                                  String comment,
                                  Integer rating) {
}
