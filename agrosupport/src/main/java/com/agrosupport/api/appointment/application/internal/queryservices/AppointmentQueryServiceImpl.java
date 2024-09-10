package com.agrosupport.api.appointment.application.internal.queryservices;

import com.agrosupport.api.appointment.domain.model.aggregates.Appointment;
import com.agrosupport.api.appointment.domain.model.queries.*;
import com.agrosupport.api.appointment.domain.services.AppointmentQueryService;
import com.agrosupport.api.appointment.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsQuery query) {
        return this.appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return this.appointmentRepository.findById(query.id());
    }

    @Override
    public List<Appointment> handle(GetAppointmentsByFarmerIdQuery query) {
        return this.appointmentRepository.findByFarmer_Id(query.farmerId());
    }

    @Override
    public List<Appointment> handle(GetAppointmentsByAdvisorIdQuery query) {
        return this.appointmentRepository.findByAdvisor_Id(query.advisorId());
    }

    @Override
    public List<Appointment> handle(GetAppointmentsByAdvisorIdAndFarmerIdQuery query) {
        return this.appointmentRepository.findByAdvisor_IdAndFarmer_Id(query.advisorId(), query.farmerId());
    }
}
