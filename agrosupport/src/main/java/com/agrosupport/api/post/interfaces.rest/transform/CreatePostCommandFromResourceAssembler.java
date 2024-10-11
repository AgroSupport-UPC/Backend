package com.agrosupport.api.post.interfaces.rest.transform;

import com.agrosupport.api.post.domain.model.commands.CreatePostCommand;
import com.agrosupport.api.post.interfaces.rest.resources.CreatePostResource;

public class CreatePostCommandFromResourceAssembler {
    public static CreatePostCommand toCommandFromResource(CreatePostResource resource) {
        return new CreatePostCommand(
                resource.advisorId(),
                resource.title(),
                resource.description(),
                resource.image());
    }
}
