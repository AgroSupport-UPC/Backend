package com.agrosupport.api.profile.domain.model.entities;

import com.agrosupport.api.iam.domain.model.aggregates.User;
import com.agrosupport.api.profile.domain.model.commands.CreateFarmerCommand;
import com.agrosupport.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Farmer() {
    }

    public Farmer(CreateFarmerCommand command, User user) {
        this.user = user;
    }

    public Long getUserId() {
        return user.getId();
    }
}
