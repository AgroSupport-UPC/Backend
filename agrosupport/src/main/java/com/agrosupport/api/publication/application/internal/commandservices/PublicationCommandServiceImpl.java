package com.agrosupport.api.publication.application.internal.commandservices;

import com.agrosupport.api.publication.application.internal.outboundservices.acl.ExternalProfileService;
import com.agrosupport.api.publication.domain.exceptions.AdvisorNotFoundException;
import com.agrosupport.api.publication.domain.exceptions.PublicationNotFoundException;
import com.agrosupport.api.publication.domain.model.aggregates.Publication;
import com.agrosupport.api.publication.domain.model.commands.CreatePublicationCommand;
import com.agrosupport.api.publication.domain.model.commands.DeletePublicationCommand;
import com.agrosupport.api.publication.domain.model.commands.UpdatePublicationCommand;
import com.agrosupport.api.publication.domain.services.PublicationCommandService;
import com.agrosupport.api.publication.infrastructure.persistence.jpa.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublicationCommandServiceImpl implements PublicationCommandService {
    private final PublicationRepository publicationRepository;
    private final ExternalProfileService externalProfileService;

    public PublicationCommandServiceImpl(PublicationRepository publicationRepository, ExternalProfileService externalProfileService) {
        this.publicationRepository = publicationRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public Long handle(CreatePublicationCommand command) {
        var advisor = externalProfileService.fetchAdvisorById(command.advisorId());
        if (advisor.isEmpty()) {
            throw new AdvisorNotFoundException(command.advisorId());
        }
        Publication publication = new Publication(command, advisor.get());
        publicationRepository.save(publication);
        return publication.getId();
    }

    @Override
    public Optional<Publication> handle(UpdatePublicationCommand command) {
        var publication = publicationRepository.findById(command.id());
        if (publication.isEmpty()) {
            return Optional.empty();
        }
        var publicationToUpdate = publication.get();
        Publication updatedPublication = publicationRepository.save(publicationToUpdate.update(command));
        return Optional.of(updatedPublication);
    }

    @Override
    public void handle(DeletePublicationCommand command) {
        var publication = publicationRepository.findById(command.id());
        if (publication.isEmpty()) {
            throw new PublicationNotFoundException(command.id());
        }
        publicationRepository.delete(publication.get());
    }
}
