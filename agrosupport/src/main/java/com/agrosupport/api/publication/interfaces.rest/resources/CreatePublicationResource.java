package com.agrosupport.api.publication.interfaces.rest.resources;

public record CreatePublicationResource(Long advisorId, String title, String description, String image) {
}
