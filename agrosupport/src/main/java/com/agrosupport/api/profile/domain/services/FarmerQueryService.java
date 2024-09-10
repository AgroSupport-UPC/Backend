package com.agrosupport.api.profile.domain.services;


import com.agrosupport.api.profile.domain.model.entities.Farmer;
import com.agrosupport.api.profile.domain.model.queries.GetAllFarmersQuery;
import com.agrosupport.api.profile.domain.model.queries.GetFarmerByIdQuery;
import com.agrosupport.api.profile.domain.model.queries.GetFarmerByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface FarmerQueryService {
    Optional<Farmer> handle(GetFarmerByIdQuery query);
    List<Farmer> handle(GetAllFarmersQuery query);
    Optional<Farmer> handle(GetFarmerByUserIdQuery query);
}
