package com.agrosupport.api.profile.interfaces.acl;

import com.agrosupport.api.profile.domain.model.aggregates.Profile;
import com.agrosupport.api.profile.domain.model.commands.CreateNotificationCommand;
import com.agrosupport.api.profile.domain.model.entities.Advisor;
import com.agrosupport.api.profile.domain.model.entities.Farmer;
import com.agrosupport.api.profile.domain.model.entities.Notification;
import com.agrosupport.api.profile.domain.model.queries.*;
import com.agrosupport.api.profile.domain.services.*;
import com.agrosupport.api.profile.interfaces.rest.resources.CreateNotificationResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfilesContextFacade {
    private final ProfileQueryService profileQueryService;
    private final FarmerQueryService farmerQueryService;
    private final AdvisorQueryService advisorQueryService;
    private final NotificationQueryService notificationQueryService;
    private final NotificationCommandService notificationCommandService;

    public ProfilesContextFacade(ProfileQueryService profileQueryService, FarmerQueryService farmerQueryService, AdvisorQueryService advisorQueryService, NotificationQueryService notificationQueryService, NotificationCommandService notificationCommandService) {
        this.profileQueryService = profileQueryService;
        this.farmerQueryService = farmerQueryService;
        this.advisorQueryService = advisorQueryService;
        this.notificationQueryService = notificationQueryService;
        this.notificationCommandService = notificationCommandService;
    }

    public Optional<Profile> fetchProfileByFarmerId(Long farmerId) {
        var farmerProfileQuery = new GetFarmerByIdQuery(farmerId);
        var farmer = farmerQueryService.handle(farmerProfileQuery);
        if (farmer.isEmpty()) return Optional.empty();
        Long userId = farmer.get().getUserId();
        var profileQuery = new GetProfileByUserIdQuery(userId);
        return profileQueryService.handle(profileQuery);
    }

    public Optional<Profile> fetchProfileByAdvisorId(Long advisorId) {
        var advisorProfileQuery = new GetAdvisorByUserIdQuery(advisorId);
        var advisor = advisorQueryService.handle(advisorProfileQuery);
        if (advisor.isEmpty()) return Optional.empty();
        Long userId = advisor.get().getUserId();
        var profileQuery = new GetProfileByUserIdQuery(userId);
        return profileQueryService.handle(profileQuery);
    }

    public Optional<Farmer> fetchFarmerById(Long farmerId) {
        var getFarmerByIdQuery = new GetFarmerByIdQuery(farmerId);
        return farmerQueryService.handle(getFarmerByIdQuery);
    }

    public Optional<Advisor> fetchAdvisorById(Long advisorId) {
        var getAdvisorByIdQuery = new GetAdvisorByUserIdQuery(advisorId);
        return advisorQueryService.handle(getAdvisorByIdQuery);
    }



    public Long createNotification(CreateNotificationResource notification) {
        return notificationCommandService.handle(new CreateNotificationCommand(notification.userId(), notification.title(), notification.message(), notification.sendAt()));
    }

}
