package com.agrosupport.api.profile.domain.services;

import com.agrosupport.api.profile.domain.model.aggregates.Profile;
import com.agrosupport.api.profile.domain.model.queries.GetAllAdvisorProfilesQuery;
import com.agrosupport.api.profile.domain.model.queries.GetAllProfilesQuery;
import com.agrosupport.api.profile.domain.model.queries.GetProfileByIdQuery;
import com.agrosupport.api.profile.domain.model.queries.GetProfileByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    List<Profile> handle(GetAllProfilesQuery query);
    Optional<Profile> handle(GetProfileByUserIdQuery query);
    List<Profile> handle(GetAllAdvisorProfilesQuery query);
}
