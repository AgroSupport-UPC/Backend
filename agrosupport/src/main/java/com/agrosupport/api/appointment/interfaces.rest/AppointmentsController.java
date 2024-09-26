package com.agrosupport.api.appointment.interfaces.rest;

import com.agrosupport.api.appointment.domain.model.aggregates.Appointment;
import com.agrosupport.api.appointment.domain.model.commands.DeleteAppointmentCommand;
import com.agrosupport.api.appointment.domain.model.events.CreateNotificationByAppointmentCreated;
import com.agrosupport.api.appointment.domain.model.queries.*;
import com.agrosupport.api.appointment.domain.services.AppointmentCommandService;
import com.agrosupport.api.appointment.domain.services.AppointmentQueryService;
import com.agrosupport.api.appointment.interfaces.rest.resources.AppointmentResource;
import com.agrosupport.api.appointment.interfaces.rest.resources.CreateAppointmentResource;
import com.agrosupport.api.appointment.interfaces.rest.resources.UpdateAppointmentResource;
import com.agrosupport.api.appointment.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.agrosupport.api.appointment.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import com.agrosupport.api.appointment.interfaces.rest.transform.UpdateAppointmentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.ApplicationEventPublisher;
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
@RequestMapping(value="api/v1/appointments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Appointments", description = "Appointment Management Endpoints")
public class AppointmentsController {
    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;
    private final ApplicationEventPublisher eventPublisher;

    public AppointmentsController(AppointmentCommandService appointmentCommandService, AppointmentQueryService appointmentQueryService, ApplicationEventPublisher eventPublisher) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResource>> getAllAppointments() {
        var getAllAppointmentsQuery = new GetAllAppointmentsQuery();
        var appointments = appointmentQueryService.handle(getAllAppointmentsQuery);
        var appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResource> getAppointmentById(@PathVariable Long id) {
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(id);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return ResponseEntity.ok(appointmentResource);
    }

    @GetMapping("/{farmerId}/farmer")
    public ResponseEntity<List<AppointmentResource>> getAppointmentsByFarmerId(@PathVariable Long farmerId) {
        var getAppointmentsByFarmerIdQuery = new GetAppointmentsByFarmerIdQuery(farmerId);
        var appointments = appointmentQueryService.handle(getAppointmentsByFarmerIdQuery);
        var appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentResources);
    }

    @GetMapping("/{advisorId}/advisor")
    public ResponseEntity<List<AppointmentResource>> getAppointmentsByAdvisorId(@PathVariable Long advisorId) {
        var getAppointmentsByAdvisorIdQuery = new GetAppointmentsByAdvisorIdQuery(advisorId);
        var appointments = appointmentQueryService.handle(getAppointmentsByAdvisorIdQuery);
        var appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentResources);
    }

    @GetMapping("/{advisorId}/advisor/{farmerId}/farmer")
    public ResponseEntity<List<AppointmentResource>> getAppointmentsByAdvisorIdAndFarmerId(@PathVariable Long advisorId, @PathVariable Long farmerId) {
        var getAppointmentsByAdvisorIdAndFarmerIdQuery = new GetAppointmentsByAdvisorIdAndFarmerIdQuery(advisorId, farmerId);
        var appointments = appointmentQueryService.handle(getAppointmentsByAdvisorIdAndFarmerIdQuery);
        var appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentResources);
    }

    @PostMapping
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource createAppointmentResource) {
        var createAppointmentCommand = CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(createAppointmentResource);
        Long appointmentId;
        try {
            appointmentId = appointmentCommandService.handle(createAppointmentCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
        if (appointmentId == 0L) return ResponseEntity.badRequest().build();
        var appointment = appointmentQueryService.handle(new GetAppointmentByIdQuery(appointmentId));
        if (appointment.isEmpty()) return ResponseEntity.badRequest().build();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        eventPublisher.publishEvent(new CreateNotificationByAppointmentCreated(this, appointmentResource.farmerId(), appointmentResource.advisorId()));
        return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResource> updateAppointment(@PathVariable Long id, @RequestBody UpdateAppointmentResource updateAppointmentResource) {
        var updateAppointmentCommand = UpdateAppointmentCommandFromResourceAssembler.toCommandFromResource(id, updateAppointmentResource);
        Optional<Appointment> appointment;
        try {
            appointment = appointmentCommandService.handle(updateAppointmentCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return ResponseEntity.ok(appointmentResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        var deleteAppointmentCommand = new DeleteAppointmentCommand(id);
        try {
            appointmentCommandService.handle(deleteAppointmentCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body("Appointment with id " + id + " deleted successfully");
    }
}
