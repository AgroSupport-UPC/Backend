package com.agrosupport.api.profile.domain.model.entities;

import com.agrosupport.api.iam.domain.model.aggregates.User;
import com.agrosupport.api.profile.domain.model.commands.CreateAdvisorCommand;
import com.agrosupport.api.profile.domain.model.commands.UpdateAdvisorCommand;
import com.agrosupport.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
public class Advisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull(message = "Rating is required")
    @Column(precision = 3, scale = 2)
    BigDecimal rating;

    public Advisor() {
        this.rating = BigDecimal.valueOf(0.00);
    }

    public Advisor(User user) {
        this.rating = BigDecimal.valueOf(0.00);
        this.user = user;
    }

    public Advisor update(UpdateAdvisorCommand command) {
        this.rating = command.rating();
        return this;
    }

    public Long getUserId() {
        return user.getId();
    }
}
