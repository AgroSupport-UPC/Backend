package com.agrosupport.api.profile.interfaces.rest.transform;

import com.agrosupport.api.profile.domain.model.commands.UpdateAdvisorCommand;
import com.agrosupport.api.profile.interfaces.rest.resources.UpdateAdvisorResource;

public class UpdateAdvisorCommandFromResourceAssembler {
    public static UpdateAdvisorCommand toCommandFromResource(Long id, UpdateAdvisorResource resource) {
        return new UpdateAdvisorCommand(
                id,
                resource.rating());
    }
}
