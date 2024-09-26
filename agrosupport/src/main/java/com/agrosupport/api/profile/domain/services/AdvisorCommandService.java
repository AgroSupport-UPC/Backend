package com.agrosupport.api.profile.domain.services;

import com.agrosupport.api.iam.domain.model.aggregates.User;
import com.agrosupport.api.profile.domain.model.commands.CreateAdvisorCommand;
import com.agrosupport.api.profile.domain.model.commands.DeleteAdvisorCommand;
import com.agrosupport.api.profile.domain.model.commands.UpdateAdvisorCommand;
import com.agrosupport.api.profile.domain.model.entities.Advisor;

import java.util.Optional;

public interface AdvisorCommandService {
    Long handle(CreateAdvisorCommand command, User user);
    Optional<Advisor> handle(UpdateAdvisorCommand command);
    void handle(DeleteAdvisorCommand command);
}
