package com.agrosupport.api.appointment.domain.services;

import com.agrosupport.api.appointment.domain.model.entities.AvailableDate;
import com.agrosupport.api.appointment.domain.model.queries.GetAllAvailableDatesQuery;
import com.agrosupport.api.appointment.domain.model.queries.GetAvailableDateByIdQuery;
import com.agrosupport.api.appointment.domain.model.queries.GetAvailableDatesByAdvisorIdQuery;

import java.util.List;
import java.util.Optional;

public interface AvailableDateQueryService {
    List<AvailableDate> handle(GetAllAvailableDatesQuery query);
    Optional<AvailableDate> handle(GetAvailableDateByIdQuery query);
    List<AvailableDate> handle(GetAvailableDatesByAdvisorIdQuery query);
}
