package com.agrosupport.api.profile.domain.services;

import com.agrosupport.api.iam.domain.model.aggregates.User;
import com.agrosupport.api.profile.domain.model.commands.CreateFarmerCommand;
import com.agrosupport.api.profile.domain.model.commands.DeleteFarmerCommand;

import java.util.Optional;

public interface FarmerCommandService {
    Long handle(CreateFarmerCommand createFarmerCommand,User user);
    void handle(DeleteFarmerCommand command);
}
