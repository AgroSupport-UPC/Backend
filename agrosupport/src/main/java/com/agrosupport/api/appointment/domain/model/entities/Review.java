package com.agrosupport.api.appointment.domain.model.entities;

import com.agrosupport.api.appointment.domain.model.aggregates.Appointment;
import com.agrosupport.api.appointment.domain.model.commands.CreateReviewCommand;
import com.agrosupport.api.appointment.domain.model.commands.UpdateReviewCommand;
import com.agrosupport.api.profile.domain.model.entities.Advisor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Min(value = 0, message = "Rating must be between 0 and 5")
    @Max(value = 5, message = "Rating must be between 0 and 5")
    @NotNull(message = "Rating is required")
    private Integer rating;

    public Review() {
    }

    public Review(CreateReviewCommand command, Advisor advisor) {
        this.comment = command.comment();
        this.rating = command.rating();
        this.advisor = advisor;
    }

    public Review update(UpdateReviewCommand command) {
        this.comment = command.comment();
        this.rating = command.rating();
        return this;
    }

    public Long getAdvisorId() {
        return advisor.getId();
    }
}
