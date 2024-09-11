package com.agrosupport.api.appointment.application.internal.queryservices;

import com.agrosupport.api.appointment.domain.model.entities.AvailableDate;
import com.agrosupport.api.appointment.domain.model.queries.GetAllAvailableDatesQuery;
import com.agrosupport.api.appointment.domain.model.queries.GetAvailableDateByIdQuery;
import com.agrosupport.api.appointment.domain.model.queries.GetAvailableDatesByAdvisorIdQuery;
import com.agrosupport.api.appointment.domain.services.AvailableDateQueryService;
import com.agrosupport.api.appointment.infrastructure.persistence.jpa.repositories.AvailableDateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailableDateQueryServiceImpl implements AvailableDateQueryService {
    private final AvailableDateRepository availableDateRepository;

    public AvailableDateQueryServiceImpl(AvailableDateRepository availableDateRepository) {
        this.availableDateRepository = availableDateRepository;
    }

    @Override
    public List<AvailableDate> handle(GetAllAvailableDatesQuery query) {
        return availableDateRepository.findAll();
    }

    @Override
    public Optional<AvailableDate> handle(GetAvailableDateByIdQuery query) {
        return availableDateRepository.findById(query.id());
    }

    @Override
    public List<AvailableDate> handle(GetAvailableDatesByAdvisorIdQuery query) {
        return availableDateRepository.findByAdvisor_Id(query.advisorId());
    }
}
