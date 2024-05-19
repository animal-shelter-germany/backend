package org.tiere.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.tiere.dto.Message;
import org.tiere.dto.MessageCreation;
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
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> get(@PathParam("userId") int userId) {
        return messageService.findAllByUser(userId);
    }

    @POST
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces
    public void post(@PathParam("userId") int opponentId, MessageCreation message) {
        messageService.save(opponentId, message);
    }

}
