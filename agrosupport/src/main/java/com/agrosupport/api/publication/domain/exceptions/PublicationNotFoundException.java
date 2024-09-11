package com.agrosupport.api.publication.domain.exceptions;

public class PublicationNotFoundException extends RuntimeException {
    public PublicationNotFoundException(Long id) {
        super("Publication with id " + id + " not found");
    }
}
