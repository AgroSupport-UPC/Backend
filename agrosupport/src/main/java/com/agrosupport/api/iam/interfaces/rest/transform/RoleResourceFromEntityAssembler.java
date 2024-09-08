package com.agrosupport.api.iam.interfaces.rest.transform;

import com.agrosupport.api.iam.domain.model.entities.Role;
import com.agrosupport.api.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}