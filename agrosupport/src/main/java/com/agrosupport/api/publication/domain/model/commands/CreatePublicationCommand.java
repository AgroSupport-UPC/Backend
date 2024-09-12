package com.agrosupport.api.publication.domain.model.commands;

public record CreatePublicationCommand(Long advisorId, String title, String description, String image) {
}
