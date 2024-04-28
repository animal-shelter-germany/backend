package org.tiere.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.tiere.dto.Listing;
import org.tiere.mapper.ListingMapper;
import org.tiere.repo.ListingRepo;

import java.util.List;

@ApplicationScoped
public class ListingService {

    private final ListingRepo listingRepo;
    private final AuthenticationService authenticationService;

    @Inject
    public ListingService(ListingRepo listingRepo, AuthenticationService authenticationService) {
        this.listingRepo = listingRepo;
        this.authenticationService = authenticationService;
    }

    public List<Listing> findAll() {
        return ListingMapper.map(listingRepo.listAll());
    }

    public Listing findById(long listingId) {
        return ListingMapper.map(listingRepo.findById(listingId));
    }

    public List<Listing> findByAccount() {
        return ListingMapper.map(listingRepo.findByAccount(authenticationService.requireAccount()));
    }
}
