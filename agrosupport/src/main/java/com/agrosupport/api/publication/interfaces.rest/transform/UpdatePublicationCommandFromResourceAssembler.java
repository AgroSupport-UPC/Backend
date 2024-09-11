package com.agrosupport.api.publication.interfaces.rest.transform;

import com.agrosupport.api.publication.domain.model.commands.UpdatePublicationCommand;
import com.agrosupport.api.publication.interfaces.rest.resources.UpdatePublicationResource;

public class UpdatePublicationCommandFromResourceAssembler {
    public static UpdatePublicationCommand toCommandFromResource(Long id, UpdatePublicationResource resource) {
        return new UpdatePublicationCommand(
                id,
                resource.title(),
                resource.description(),
                resource.image());
    }
}
