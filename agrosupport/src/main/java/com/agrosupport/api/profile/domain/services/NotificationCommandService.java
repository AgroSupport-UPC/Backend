package com.agrosupport.api.profile.domain.services;

import com.agrosupport.api.profile.domain.model.commands.CreateNotificationCommand;
import com.agrosupport.api.profile.domain.model.commands.DeleteNotificationCommand;

public interface NotificationCommandService {
    Long handle(CreateNotificationCommand command);
    void handle(DeleteNotificationCommand command);
}
