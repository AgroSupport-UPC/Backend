package com.agrosupport.api.appointment.interfaces.rest.transform;

import com.agrosupport.api.appointment.domain.model.commands.CreateReviewCommand;
import com.agrosupport.api.appointment.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {
    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource){
        return new CreateReviewCommand(
                resource.advisorId(),
                resource.farmerId(),
                resource.comment(),
                resource.rating()
        );
    }
}
