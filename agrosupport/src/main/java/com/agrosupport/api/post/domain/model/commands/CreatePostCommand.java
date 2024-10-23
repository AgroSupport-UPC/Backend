package com.agrosupport.api.post.domain.model.commands;

public record CreatePostCommand(Long advisorId, String title, String description, String image) {
}
