package com.agrosupport.api.profile.application.internal.queryservices;

import com.agrosupport.api.profile.domain.model.aggregates.Profile;
import com.agrosupport.api.profile.domain.model.queries.GetAllAdvisorProfilesQuery;
import com.agrosupport.api.profile.domain.model.queries.GetAllProfilesQuery;
import com.agrosupport.api.profile.domain.model.queries.GetProfileByIdQuery;
import com.agrosupport.api.profile.domain.model.queries.GetProfileByUserIdQuery;
import com.agrosupport.api.profile.domain.services.ProfileQueryService;
import com.agrosupport.api.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.id());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> handle(GetProfileByUserIdQuery query) {
        return profileRepository.findByUser_Id(query.userId());
    }

    @Override
    public List<Profile> handle(GetAllAdvisorProfilesQuery query){
        return profileRepository.findAllAdvisorProfiles();
    }
}
