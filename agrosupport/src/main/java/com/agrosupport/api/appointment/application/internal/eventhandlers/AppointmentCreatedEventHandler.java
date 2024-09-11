package com.agrosupport.api.appointment.application.internal.eventhandlers;

import com.agrosupport.api.appointment.application.internal.outboundservices.acl.ExternalProfilesService;
import com.agrosupport.api.appointment.domain.model.events.CreateNotificationByAppointmentCreated;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AppointmentCreatedEventHandler {
    private final ExternalProfilesService externalProfilesService;

    public AppointmentCreatedEventHandler(ExternalProfilesService externalProfileService) {
        this.externalProfilesService = externalProfileService;
    }

    @EventListener
    public void onAppointmentCreated(CreateNotificationByAppointmentCreated event) {
        Date date = new Date();

        var farmer = externalProfilesService.fetchFarmerById(event.getFarmerId()).orElseThrow();
        var advisor = externalProfilesService.fetchAdvisorById(event.getAdvisorId()).orElseThrow();
        var profileFarmer = externalProfilesService.fetchProfileByFarmerId(event.getFarmerId()).orElseThrow();
        var profileAdvisor = externalProfilesService.fetchProfileByAdvisorId(event.getAdvisorId()).orElseThrow();

        var meetingUrl = "https://meet.jit.si/agrosupportMeeting" + event.getFarmerId() + "-" + event.getAdvisorId();
        externalProfilesService.createNotification(farmer.getUserId(), "Proximo Asesoramiento",
                "Tienes un asesoramiento programado con " + profileAdvisor.getFirstName() + " " + profileAdvisor.getLastName(),
                date);
        externalProfilesService.createNotification(advisor.getUserId(), "Proximo Asesoramiento",
                "Tienes una asesoria programada con " + profileFarmer.getFirstName() + " " + profileFarmer.getLastName(),
                date);
    }

}