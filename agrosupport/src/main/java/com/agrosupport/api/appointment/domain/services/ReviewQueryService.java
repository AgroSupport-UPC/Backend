package com.agrosupport.api.appointment.domain.services;

import com.agrosupport.api.appointment.domain.model.entities.Review;
import com.agrosupport.api.appointment.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    List<Review> handle(GetAllReviewsQuery query);
    Optional<Review> handle(GetReviewByIdQuery query);
    List<Review> handle(GetReviewByAdvisorIdQuery query);
    List<Review> handle(GetReviewByFarmerIdQuery query);
    Optional<Review> handle(GetReviewByAdvisorIdAndFarmerIdQuery query);
}
