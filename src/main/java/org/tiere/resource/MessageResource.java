package org.tiere.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.tiere.dto.Message;
import org.tiere.service.MessageService;

import java.util.List;

@Path("/messages")
public class MessageResource {

    private final MessageService messageService;

    @Inject
    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @GET
    @Path("/{userId}")
    public List<Message> get(@PathParam("userId") int userId) {
        return messageService.findAllByUser(userId);
    }

}
