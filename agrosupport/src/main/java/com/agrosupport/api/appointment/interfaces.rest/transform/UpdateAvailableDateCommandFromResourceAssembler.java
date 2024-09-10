package com.agrosupport.api.appointment.interfaces.rest.transform;

import com.agrosupport.api.appointment.domain.model.commands.UpdateAvailableDateCommand;
import com.agrosupport.api.appointment.interfaces.rest.resources.UpdateAvailableDateResource;

public class UpdateAvailableDateCommandFromResourceAssembler {
    public static UpdateAvailableDateCommand toCommandFromResource(Long id, UpdateAvailableDateResource resource){
        return new UpdateAvailableDateCommand(
                id,
                resource.availableDate(),
                resource.startTime(),
                resource.endTime()
        );
    }
}
