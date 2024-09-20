package com.agrosupport.api.iam.domain.exceptions;

import com.agrosupport.api.iam.domain.model.valueobjects.Roles;

import java.util.List;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String role) {
        super("Role not found: " + role + ". Please use one of the list: " + List.of(Roles.values()));
    }
}