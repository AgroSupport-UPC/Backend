package com.agrosupport.api.appointment.interfaces.rest.transform;

import com.agrosupport.api.appointment.domain.model.commands.CreateAppointmentCommand;
import com.agrosupport.api.appointment.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource){
        return new CreateAppointmentCommand(
                resource.advisorId(),
                resource.farmerId(),
                resource.message(),
                resource.status(),
                resource.scheduledDate(),
                resource.startTime(),
                resource.endTime()
        );
    }
}
