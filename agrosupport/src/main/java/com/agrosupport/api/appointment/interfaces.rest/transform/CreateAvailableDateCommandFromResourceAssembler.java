package com.agrosupport.api.appointment.interfaces.rest.transform;

import com.agrosupport.api.appointment.domain.model.commands.CreateAvailableDateCommand;
import com.agrosupport.api.appointment.interfaces.rest.resources.CreateAvailableDateResource;

public class CreateAvailableDateCommandFromResourceAssembler {
    public static CreateAvailableDateCommand toCommandFromResource(CreateAvailableDateResource resource){
        return new CreateAvailableDateCommand(
                resource.advisorId(),
                resource.availableDate(),
                resource.startTime(),
                resource.endTime()
        );
    }
}
