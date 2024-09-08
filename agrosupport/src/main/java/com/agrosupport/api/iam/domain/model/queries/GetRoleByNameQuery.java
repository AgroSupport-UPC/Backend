package com.agrosupport.api.iam.domain.model.queries;

import com.agrosupport.api.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}