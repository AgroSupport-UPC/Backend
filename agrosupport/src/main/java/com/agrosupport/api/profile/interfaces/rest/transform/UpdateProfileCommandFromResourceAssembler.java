package com.agrosupport.api.profile.interfaces.rest.transform;

import com.agrosupport.api.profile.domain.model.commands.UpdateProfileCommand;
import com.agrosupport.api.profile.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(Long id, UpdateProfileResource resource) {
        return new UpdateProfileCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.city(),
                resource.country(),
                resource.birthDate(),
                resource.description(),
                resource.photo(),
                resource.occupation(),
                resource.experience());
    }
}
