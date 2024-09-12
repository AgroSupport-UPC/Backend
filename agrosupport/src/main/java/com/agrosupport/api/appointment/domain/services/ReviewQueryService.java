package com.agrosupport.api.appointment.domain.services;

import com.agrosupport.api.appointment.domain.model.entities.Review;
import com.agrosupport.api.appointment.domain.model.queries.GetAllReviewsQuery;
import com.agrosupport.api.appointment.domain.model.queries.GetReviewByAdvisorIdAndFarmerIdQuery;
import com.agrosupport.api.appointment.domain.model.queries.GetReviewByAdvisorIdQuery;
import com.agrosupport.api.appointment.domain.model.queries.GetReviewByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    List<Review> handle(GetAllReviewsQuery query);
    Optional<Review> handle(GetReviewByIdQuery query);
    List<Review> handle(GetReviewByAdvisorIdQuery query);
    Optional<Review> handle(GetReviewByAdvisorIdAndFarmerIdQuery query);
}
