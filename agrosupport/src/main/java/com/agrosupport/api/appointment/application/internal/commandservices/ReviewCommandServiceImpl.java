package com.agrosupport.api.appointment.application.internal.commandservices;

import com.agrosupport.api.appointment.application.internal.outboundservices.acl.ExternalProfilesService;
import com.agrosupport.api.appointment.domain.exceptions.AdvisorNotFoundException;
import com.agrosupport.api.appointment.domain.exceptions.InvalidRatingException;
import com.agrosupport.api.appointment.domain.exceptions.ReviewNotFoundException;
import com.agrosupport.api.appointment.domain.model.commands.CreateReviewCommand;
import com.agrosupport.api.appointment.domain.model.commands.DeleteReviewCommand;
import com.agrosupport.api.appointment.domain.model.commands.UpdateReviewCommand;
import com.agrosupport.api.appointment.domain.model.entities.Review;
import com.agrosupport.api.appointment.domain.services.ReviewCommandService;
import com.agrosupport.api.appointment.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final ExternalProfilesService externalProfilesService;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository, ExternalProfilesService externalProfilesService) {
        this.reviewRepository = reviewRepository;
        this.externalProfilesService = externalProfilesService;
    }

    @Override
    public Long handle(CreateReviewCommand command) {
        var advisor = externalProfilesService.fetchAdvisorById(command.advisorId());
        if (advisor.isEmpty()) throw new AdvisorNotFoundException(command.advisorId());
        if(command.rating() < 0 || command.rating() > 5) throw new InvalidRatingException(command.rating());
        Review review = new Review(command, advisor.get());
        reviewRepository.save(review);
        return review.getId();
    }

    @Override
    public Optional<Review> handle(UpdateReviewCommand command) {
        var review = reviewRepository.findById(command.id());
        if (review.isEmpty()) return Optional.empty();
        if(command.rating() < 0 || command.rating() > 5) throw new InvalidRatingException(command.rating());
        var reviewToUpdate = review.get();
        reviewRepository.save(reviewToUpdate.update(command));
        return Optional.of(reviewToUpdate);
    }

    @Override
    public void handle(DeleteReviewCommand command) {
        var review = reviewRepository.findById(command.id());
        if (review.isEmpty()) throw new ReviewNotFoundException(command.id());
        reviewRepository.delete(review.get());
    }
}
