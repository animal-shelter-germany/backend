package org.tiere.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.tiere.dto.Chat;
import org.tiere.service.ChatService;

import java.util.List;

@Path("/chats")
public class ChatResource {

    private final ChatService chatService;

    @Inject
    public ChatResource(ChatService chatService) {
        this.chatService = chatService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Chat> get() {
        return chatService.findAllByUser();
    }

}
