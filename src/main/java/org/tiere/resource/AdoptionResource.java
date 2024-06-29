package org.tiere.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.tiere.dto.Adoption;
import org.tiere.dto.AdoptionCreation;
import org.tiere.entity.AdoptionAnimalEntity;
import org.tiere.entity.AdoptionEntity;
import org.tiere.repo.AdoptionRepo;
import org.tiere.repo.AnimalRepo;
import org.tiere.repo.ListingRepo;
import org.tiere.service.AdoptionService;
import org.tiere.service.AuthenticationService;
import org.tiere.util.AdoptionStatus;

import java.util.List;

@Path("/adoptions")
public class AdoptionResource {

    private final AdoptionRepo adoptionRepo;
    private final AnimalRepo animalRepo;
    private final AuthenticationService authenticationService;
    private final AdoptionService adoptionService;
    private final ListingRepo listingRepo;

    @Inject
    public AdoptionResource(AdoptionRepo adoptionRepo, AnimalRepo animalRepo, AuthenticationService authenticationService, AdoptionService adoptionService, ListingRepo listingRepo) {
        this.adoptionRepo = adoptionRepo;
        this.animalRepo = animalRepo;
        this.authenticationService = authenticationService;
        this.adoptionService = adoptionService;
        this.listingRepo = listingRepo;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Adoption> get() {
        return adoptionService.findByUser();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces
    @Transactional
    public void post(AdoptionCreation adoption) {
        AdoptionEntity adoptionEntity = new AdoptionEntity(listingRepo.findById(adoption.listing().id()), AdoptionStatus.PENDING, authenticationService.requireAccount());
        adoption.animals().forEach(item -> {
            AdoptionAnimalEntity adoptionAnimal = new AdoptionAnimalEntity();
            adoptionAnimal.setAnimal(animalRepo.findById(item.id()));
            adoptionEntity.addAdoptionAnimal(adoptionAnimal);
        });
        adoptionRepo.persist(adoptionEntity);
    }
}
