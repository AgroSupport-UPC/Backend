package com.agrosupport.api.profile.domain.services;

import com.agrosupport.api.profile.domain.model.commands.CreateFarmerCommand;
import com.agrosupport.api.profile.domain.model.commands.DeleteFarmerCommand;

import java.util.Optional;

public interface FarmerCommandService {
    Long handle(CreateFarmerCommand command);
    void handle(DeleteFarmerCommand command);
}
