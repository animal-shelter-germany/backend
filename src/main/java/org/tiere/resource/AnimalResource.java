package org.tiere.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.tiere.dto.Animal;
import org.tiere.dto.AnimalCreation;
import org.tiere.dto.AnimalUpdate;
import org.tiere.service.AnimalService;

@Path("/animals")
public class AnimalResource {

    private final AnimalService animalService;

    public AnimalResource(AnimalService animalService) {
        this.animalService = animalService;
    }

    @DELETE
    @Path("/{animalId}")
    @Produces
    public void delete(@PathParam("animalId") int animalId) {
        animalService.delete(animalId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Animal post(@QueryParam("listing") int listingId, AnimalCreation animal) {
        return animalService.create(listingId, animal);
    }

    @PUT
    @Path("/{animalId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces
    public void update(@PathParam("animalId") int id, AnimalUpdate animal) {
        animalService.update(id, animal);
    }

}
