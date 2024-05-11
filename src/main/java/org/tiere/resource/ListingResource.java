package org.tiere.resource;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.tiere.dto.Listing;
import org.tiere.dto.ListingCreation;
import org.tiere.service.ListingService;

import java.io.IOException;
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
    @Path("/latest/{count}")
    @Consumes
    @Produces(MediaType.APPLICATION_JSON)
    public List<Listing> getLatest(@PathParam("count") int count) {
        return listingService.findLatest(count);
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces
    public void post(ListingCreation listing) throws IOException {
        listingService.save(listing);
    }

    @DELETE
    @Path("/{listingId}")
    @Consumes
    @Produces
    public void delete(@PathParam("listingId") Integer listingId) {
        listingService.delete(listingId);
    }

}
