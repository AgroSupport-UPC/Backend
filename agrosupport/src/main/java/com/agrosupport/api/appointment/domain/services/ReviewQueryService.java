package com.agrosupport.api.appointment.domain.services;

import com.agrosupport.api.appointment.domain.model.entities.Review;
import com.agrosupport.api.appointment.domain.model.queries.GetAllReviewsQuery;
import com.agrosupport.api.appointment.domain.model.queries.GetReviewByAdvisorIdQuery;
import com.agrosupport.api.appointment.domain.model.queries.GetReviewByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    List<Review> handle(GetAllReviewsQuery query);
    Optional<Review> handle(GetReviewByIdQuery query);
    Optional<Review> handle(GetReviewByAdvisorIdQuery query);
}
