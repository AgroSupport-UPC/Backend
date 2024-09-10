package com.agrosupport.api.profile.application.internal.commandservices;

import com.agrosupport.api.profile.application.internal.outboundservices.acl.ExternalUserService;
import com.agrosupport.api.profile.domain.exceptions.FarmerNotFoundException;
import com.agrosupport.api.profile.domain.exceptions.UserNotFoundException;
import com.agrosupport.api.profile.domain.model.commands.CreateFarmerCommand;
import com.agrosupport.api.profile.domain.model.commands.DeleteFarmerCommand;
import com.agrosupport.api.profile.domain.model.entities.Farmer;
import com.agrosupport.api.profile.domain.services.FarmerCommandService;
import com.agrosupport.api.profile.infrastructure.persistence.jpa.repositories.FarmerRepository;
import org.springframework.stereotype.Service;

@Service
public class FarmerCommandServiceImpl implements FarmerCommandService {
    private final FarmerRepository farmerRepository;
    private final ExternalUserService externalUserService;

    public FarmerCommandServiceImpl(FarmerRepository farmerRepository, ExternalUserService externalUserService) {
        this.farmerRepository = farmerRepository;
        this.externalUserService = externalUserService;
    }

    @Override
    public Long handle(CreateFarmerCommand command) {
        var user = externalUserService.fetchUserById(command.userId());
        if (user.isEmpty()) {
            throw new UserNotFoundException(command.userId());
        }
        var sameUser = farmerRepository.findByUser_Id(command.userId());
        if (sameUser.isPresent()) {
            throw new UserNotFoundException(command.userId());
        }
        var farmer = new Farmer(command, user.get());
        farmerRepository.save(farmer);
        return farmer.getId();
    }

    @Override
    public void handle(DeleteFarmerCommand command) {
        var farmer = farmerRepository.findById(command.id());
        if (farmer.isEmpty()) {
            throw new FarmerNotFoundException(command.id());
        }
        farmerRepository.delete(farmer.get());
    }
}
