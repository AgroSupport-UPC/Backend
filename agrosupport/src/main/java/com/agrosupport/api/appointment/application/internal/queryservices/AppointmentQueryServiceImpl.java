package com.agrosupport.api.appointment.application.internal.queryservices;

import com.agrosupport.api.appointment.domain.model.aggregates.Appointment;
import com.agrosupport.api.appointment.domain.model.commands.UpdateAppointmentCommand;
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
        List<Appointment> appointments = this.appointmentRepository.findAll();
        updateAppointmentStatuses(appointments);
        return appointments;
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        Optional<Appointment> appointment = this.appointmentRepository.findById(query.id());
        appointment.ifPresent(this::updateAppointmentStatus);
        return appointment;
    }

    @Override
    public List<Appointment> handle(GetAppointmentsByFarmerIdQuery query) {
        List<Appointment> appointments = this.appointmentRepository.findByFarmer_Id(query.farmerId());
        updateAppointmentStatuses(appointments);
        return appointments;
    }

    @Override
    public List<Appointment> handle(GetAppointmentsByAdvisorIdQuery query) {
        List<Appointment> appointments = this.appointmentRepository.findByAdvisor_Id(query.advisorId());
        updateAppointmentStatuses(appointments);
        return appointments;
    }

    @Override
    public List<Appointment> handle(GetAppointmentsByAdvisorIdAndFarmerIdQuery query) {
        List<Appointment> appointments = this.appointmentRepository.findByAdvisor_IdAndFarmer_Id(query.advisorId(), query.farmerId());
        updateAppointmentStatuses(appointments);
        return appointments;
    }

    private void updateAppointmentStatuses(List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            updateAppointmentStatus(appointment);
        }
    }

    private void updateAppointmentStatus(Appointment appointment) {
        if (appointment.getScheduledDate().isBefore(java.time.LocalDate.now())) {
            var updateAppointmentCommand = new UpdateAppointmentCommand(
                    appointment.getId(),
                    appointment.getMessage(),
                    "COMPLETED",
                    appointment.getScheduledDate(),
                    appointment.getStartTime(),
                    appointment.getEndTime()
            );
            appointment.update(updateAppointmentCommand);
            this.appointmentRepository.save(appointment);
        }
    }
}
