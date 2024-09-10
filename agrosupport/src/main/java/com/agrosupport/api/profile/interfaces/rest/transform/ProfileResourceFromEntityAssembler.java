package com.agrosupport.api.profile.interfaces.rest.transform;

import com.agrosupport.api.profile.domain.model.aggregates.Profile;
import com.agrosupport.api.profile.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getUserId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getCity(),
                entity.getCountry(),
                entity.getBirthDate(),
                entity.getDescription(),
                entity.getPhoto(),
                entity.getOccupation(),
                entity.getExperience());
    }
}
