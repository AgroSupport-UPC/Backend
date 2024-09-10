package com.agrosupport.api.profile.domain.services;

import com.agrosupport.api.profile.domain.model.aggregates.Profile;
import com.agrosupport.api.profile.domain.model.commands.CreateProfileCommand;
import com.agrosupport.api.profile.domain.model.commands.DeleteProfileCommand;
import com.agrosupport.api.profile.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Long handle(CreateProfileCommand command);
    Optional<Profile> handle(UpdateProfileCommand command);
    void handle(DeleteProfileCommand command);
}
