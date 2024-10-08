package com.agrosupport.api.appointment.interfaces.rest.transform;

import com.agrosupport.api.appointment.domain.model.commands.UpdateAppointmentCommand;
import com.agrosupport.api.appointment.interfaces.rest.resources.UpdateAppointmentResource;

public class UpdateAppointmentCommandFromResourceAssembler {
    public static UpdateAppointmentCommand toCommandFromResource(Long id, UpdateAppointmentResource resource){
        return new UpdateAppointmentCommand(
                id,
                resource.message(),
                resource.status(),
                resource.scheduledDate(),
                resource.startTime(),
                resource.endTime()
        );
    }
}
