package com.agrosupport.api.profile.application.internal.queryservices;

import com.agrosupport.api.profile.domain.model.entities.Farmer;
import com.agrosupport.api.profile.domain.model.queries.GetAllFarmersQuery;
import com.agrosupport.api.profile.domain.model.queries.GetFarmerByIdQuery;
import com.agrosupport.api.profile.domain.model.queries.GetFarmerByUserIdQuery;
import com.agrosupport.api.profile.domain.services.FarmerQueryService;
import com.agrosupport.api.profile.infrastructure.persistence.jpa.repositories.FarmerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerQueryServiceImpl implements FarmerQueryService {
    private final FarmerRepository farmerRepository;

    public FarmerQueryServiceImpl(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    @Override
    public Optional<Farmer> handle(GetFarmerByIdQuery query) {
        return farmerRepository.findById(query.id());
    }

    @Override
    public Optional<Farmer> handle(GetFarmerByUserIdQuery query) {
        return farmerRepository.findByUser_Id(query.userId());
    }

    @Override
    public List<Farmer> handle(GetAllFarmersQuery query) {
        return farmerRepository.findAll();
    }

}
