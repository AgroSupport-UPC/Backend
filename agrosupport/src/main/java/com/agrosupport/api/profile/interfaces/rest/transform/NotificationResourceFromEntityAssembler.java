package com.agrosupport.api.profile.interfaces.rest.transform;

import com.agrosupport.api.profile.domain.model.entities.Notification;
import com.agrosupport.api.profile.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity) {
        return new NotificationResource(
                entity.getId(),
                entity.getUserId(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getSendAt());
    }
}
