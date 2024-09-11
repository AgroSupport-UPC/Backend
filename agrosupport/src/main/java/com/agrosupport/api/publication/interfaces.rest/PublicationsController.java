package com.agrosupport.api.publication.interfaces.rest;

import com.agrosupport.api.publication.domain.model.aggregates.Publication;
import com.agrosupport.api.publication.domain.model.commands.DeletePublicationCommand;
import com.agrosupport.api.publication.domain.model.queries.GetAllPublicationsQuery;
import com.agrosupport.api.publication.domain.model.queries.GetPublicationByIdQuery;
import com.agrosupport.api.publication.domain.services.PublicationCommandService;
import com.agrosupport.api.publication.domain.services.PublicationQueryService;
import com.agrosupport.api.publication.interfaces.rest.resources.CreatePublicationResource;
import com.agrosupport.api.publication.interfaces.rest.resources.PublicationResource;
import com.agrosupport.api.publication.interfaces.rest.resources.UpdatePublicationResource;
import com.agrosupport.api.publication.interfaces.rest.transform.CreatePublicationCommandFromResourceAssembler;
import com.agrosupport.api.publication.interfaces.rest.transform.PublicationResourceFromEntityAssembler;
import com.agrosupport.api.publication.interfaces.rest.transform.UpdatePublicationCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="api/v1/publications", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Publications", description = "Publication Management Endpoints")
public class PublicationsController {
    private final PublicationCommandService publicationCommandService;
    private final PublicationQueryService publicationQueryService;

    public PublicationsController(PublicationCommandService publicationCommandService, PublicationQueryService publicationQueryService) {
        this.publicationCommandService = publicationCommandService;
        this.publicationQueryService = publicationQueryService;
    }

    @GetMapping
    public ResponseEntity<List<PublicationResource>> getAllPublications() {
        var getAllPublicationsQuery = new GetAllPublicationsQuery();
        var publications = publicationQueryService.handle(getAllPublicationsQuery);
        var publicationResources = publications.stream().map(PublicationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(publicationResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationResource> getPublicationById(@PathVariable Long id) {
        var getPublicationByIdQuery = new GetPublicationByIdQuery(id);
        var publication = publicationQueryService.handle(getPublicationByIdQuery);
        if (publication.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var publicationResource = PublicationResourceFromEntityAssembler.toResourceFromEntity(publication.get());
        return ResponseEntity.ok(publicationResource);
    }

    @PostMapping
    public ResponseEntity<PublicationResource> createPublication(@RequestBody CreatePublicationResource createPublicationResource) {
        var createPublicationCommand = CreatePublicationCommandFromResourceAssembler.toCommandFromResource(createPublicationResource);
        var publicationId = publicationCommandService.handle(createPublicationCommand);
        if (publicationId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getPublicationByIdQuery = new GetPublicationByIdQuery(publicationId);
        var publication = publicationQueryService.handle(getPublicationByIdQuery);
        if (publication.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var publicationResource = PublicationResourceFromEntityAssembler.toResourceFromEntity(publication.get());
        return new ResponseEntity<>(publicationResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationResource> updatePublication(@PathVariable Long id, @RequestBody UpdatePublicationResource updatePublicationResource) {
        var updatePublicationCommand = UpdatePublicationCommandFromResourceAssembler.toCommandFromResource(id, updatePublicationResource);
        Optional<Publication> publication;
        try {
            publication = publicationCommandService.handle(updatePublicationCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
        if (publication.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var publicationResource = PublicationResourceFromEntityAssembler.toResourceFromEntity(publication.get());
        return ResponseEntity.ok(publicationResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublication(@PathVariable Long id) {
        var deletePublicationCommand = new DeletePublicationCommand(id);
        try{
            publicationCommandService.handle(deletePublicationCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok("Publication with id " + id + " successfully deleted");
    }
}