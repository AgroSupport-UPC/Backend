package com.agrosupport.api.iam.domain.services;

import com.agrosupport.api.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}