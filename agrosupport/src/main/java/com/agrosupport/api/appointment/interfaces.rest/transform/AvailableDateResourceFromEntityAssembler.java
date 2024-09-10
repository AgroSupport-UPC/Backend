package com.agrosupport.api.appointment.interfaces.rest.transform;

import com.agrosupport.api.appointment.domain.model.entities.AvailableDate;
import com.agrosupport.api.appointment.interfaces.rest.resources.AvailableDateResource;

public class AvailableDateResourceFromEntityAssembler {
    public static AvailableDateResource toResourceFromEntity(AvailableDate entity){
        return new AvailableDateResource(
                entity.getId(),
                entity.getAdvisorId(),
                entity.getAvailableDate(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }
}
