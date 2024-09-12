package com.agrosupport.api.appointment.interfaces.rest.transform;

import com.agrosupport.api.appointment.domain.model.commands.UpdateReviewCommand;
import com.agrosupport.api.appointment.interfaces.rest.resources.UpdateReviewResource;

public class UpdateReviewCommandFromResourceAssembler {
    public static UpdateReviewCommand toCommandFromResource(Long id, UpdateReviewResource resource){
        return new UpdateReviewCommand(
                id,
                resource.comment(),
                resource.rating()
        );
    }
}
