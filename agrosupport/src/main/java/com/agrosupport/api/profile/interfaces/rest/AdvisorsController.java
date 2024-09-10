package com.agrosupport.api.profile.interfaces.rest;

import com.agrosupport.api.profile.domain.model.commands.DeleteAdvisorCommand;
import com.agrosupport.api.profile.domain.model.entities.Advisor;
import com.agrosupport.api.profile.domain.model.queries.GetAdvisorByIdQuery;
import com.agrosupport.api.profile.domain.model.queries.GetAllAdvisorsQuery;
import com.agrosupport.api.profile.domain.services.AdvisorCommandService;
import com.agrosupport.api.profile.domain.services.AdvisorQueryService;
import com.agrosupport.api.profile.interfaces.rest.resources.*;
import com.agrosupport.api.profile.interfaces.rest.transform.AdvisorResourceFromEntityAssembler;
import com.agrosupport.api.profile.interfaces.rest.transform.CreateAdvisorCommandFromResourceAssembler;
import com.agrosupport.api.profile.interfaces.rest.transform.UpdateAdvisorCommandFromResourceAssembler;
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
@RequestMapping(value="api/v1/advisors", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Advisors", description = "Advisor Management Endpoints")
public class AdvisorsController {
    private final AdvisorCommandService advisorCommandService;
    private final AdvisorQueryService advisorQueryService;

    public AdvisorsController(AdvisorCommandService advisorCommandService, AdvisorQueryService advisorQueryService) {
        this.advisorCommandService = advisorCommandService;
        this.advisorQueryService = advisorQueryService;
    }

    @GetMapping
    public ResponseEntity<List<AdvisorResource>> getAllAdvisors() {
        var getAllAdvisorsQuery = new GetAllAdvisorsQuery();
        var advisors = advisorQueryService.handle(getAllAdvisorsQuery);
        var advisorResources = advisors.stream().map(AdvisorResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(advisorResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvisorResource> getAdvisorById(@PathVariable Long id) {
        var getAdvisorByIdQuery = new GetAdvisorByIdQuery(id);
        var advisor = advisorQueryService.handle(getAdvisorByIdQuery);
        if (advisor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var advisorResource = AdvisorResourceFromEntityAssembler.toResourceFromEntity(advisor.get());
        return ResponseEntity.ok(advisorResource);
    }

    @PostMapping
    public ResponseEntity<AdvisorResource> createAdvisor(@RequestBody CreateAdvisorResource createAdvisorResource) {
        var createAdvisorCommand = CreateAdvisorCommandFromResourceAssembler.toCommandFromResource(createAdvisorResource);
        Long advisorId;
        try {
            advisorId = advisorCommandService.handle(createAdvisorCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
        if(advisorId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var advisor = advisorQueryService.handle(new GetAdvisorByIdQuery(advisorId));
        if(advisor.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var advisorResource = AdvisorResourceFromEntityAssembler.toResourceFromEntity(advisor.get());
        return new ResponseEntity<>(advisorResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdvisorResource> updateAdvisor(@PathVariable Long id, @RequestBody UpdateAdvisorResource updateAdvisorResource) {
        var updateAdvisorCommand = UpdateAdvisorCommandFromResourceAssembler.toCommandFromResource(id, updateAdvisorResource);
        Optional<Advisor> advisor;
        try {
            advisor = advisorCommandService.handle(updateAdvisorCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
        if (advisor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var advisorResource = AdvisorResourceFromEntityAssembler.toResourceFromEntity(advisor.get());
        return ResponseEntity.ok(advisorResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdvisor(@PathVariable Long id) {
        var deleteAdvisorCommand = new DeleteAdvisorCommand(id);
        try {
            advisorCommandService.handle(deleteAdvisorCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body("Advisor with id " + id + " deleted successfully");
    }
}
