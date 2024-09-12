package com.agrosupport.api.publication.domain.model.aggregates;

import com.agrosupport.api.profile.domain.model.entities.Advisor;
import com.agrosupport.api.publication.domain.model.commands.CreatePublicationCommand;
import com.agrosupport.api.publication.domain.model.commands.UpdatePublicationCommand;
import com.agrosupport.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class Publication extends AuditableAbstractAggregateRoot<Publication> {
    @NotNull(message = "Title is required")
    @NotBlank(message = "Title cannot be blank")
    @Max(50)
    private String title;
    @NotNull(message = "Description is required")
    @NotBlank(message = "Description cannot be blank")
    @Column(columnDefinition = "TEXT")
    private String description;
    @NotNull(message = "Image is required")
    private String image;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    public Publication() {
    }

    public Publication(CreatePublicationCommand command, Advisor advisor){
        this.title = command.title();
        this.description = command.description();
        this.image = command.image();
        this.advisor = advisor;
    }

    public Publication update(UpdatePublicationCommand command){
        this.title = command.title();
        this.description = command.description();
        this.image = command.image();
        return this;
    }

    public Long getAdvisorId(){
        return this.advisor.getId();
    }

}
