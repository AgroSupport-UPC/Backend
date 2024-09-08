package com.agrosupport.api.iam.domain.services;

import com.agrosupport.api.iam.domain.model.entities.Role;
import com.agrosupport.api.iam.domain.model.queries.GetAllRolesQuery;
import com.agrosupport.api.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}