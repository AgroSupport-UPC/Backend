package com.agrosupport.api.publication.interfaces.rest.transform;

import com.agrosupport.api.publication.domain.model.aggregates.Publication;
import com.agrosupport.api.publication.interfaces.rest.resources.PublicationResource;

public class PublicationResourceFromEntityAssembler {
    public static PublicationResource toResourceFromEntity(Publication entity) {
        return new PublicationResource(
                entity.getId(),
                entity.getAdvisorId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getImage());
    }
}
