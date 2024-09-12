package com.agrosupport.api.appointment.application.internal.outboundservices.acl;

import com.agrosupport.api.profile.domain.model.aggregates.Profile;
import com.agrosupport.api.profile.domain.model.entities.Advisor;
import com.agrosupport.api.profile.domain.model.entities.Farmer;
import com.agrosupport.api.profile.interfaces.acl.ProfilesContextFacade;
import com.agrosupport.api.profile.interfaces.rest.resources.CreateNotificationResource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ExternalProfilesService {
    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfilesService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public Optional<Advisor> fetchAdvisorById(Long advisorId) {
        return profilesContextFacade.fetchAdvisorById(advisorId);
    }

    public Optional<Farmer> fetchFarmerById(Long farmerId) {
        return profilesContextFacade.fetchFarmerById(farmerId);
    }

    public Optional<Profile> fetchProfileByFarmerId(Long farmerId) {
        return profilesContextFacade.fetchProfileByFarmerId(farmerId);
    }

    public Optional<Profile> fetchProfileByAdvisorId(Long advisorId) {
        return profilesContextFacade.fetchProfileByAdvisorId(advisorId);
    }

    public Long createNotification(Long userId, String title, String message, Date sendAt) {
        return profilesContextFacade.createNotification(new CreateNotificationResource(userId, title, message, sendAt));
    }

    public void updateRating(Long advisorId, int rating) {
        profilesContextFacade.updateRating(advisorId, rating);
    }


}
