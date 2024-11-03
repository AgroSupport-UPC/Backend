package com.agrosupport.api.profile.interfaces.rest.resources;

import java.math.BigDecimal;

public record AdvisorResource(Long id,
                              Long userId,
                              BigDecimal rating) {
}
