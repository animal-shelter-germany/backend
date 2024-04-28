package org.tiere.resource;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.tiere.dto.Listing;
import org.tiere.service.ListingService;

import java.util.List;

@Path("/listings")
public class ListingResource {

    private final ListingService listingService;

    @Inject
    public ListingResource(ListingService listingService) {
        this.listingService = listingService;
    }

    @GET
    @Consumes
    @Produces(MediaType.APPLICATION_JSON)
    public List<Listing> getAll() {
        return listingService.findAll();
    }

    @GET
    @Path("/{listingId}")
    @Consumes
    @Produces(MediaType.APPLICATION_JSON)
    public Listing getById(@PathParam("listingId") int listingId) {
        return listingService.findById(listingId);
    }

    @GET
    @Path("/account")
    @Consumes
    @Produces(MediaType.APPLICATION_JSON)
    public List<Listing> getByAccount() {
        return listingService.findByAccount();
    }

}
