package org.tiere.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.tiere.dto.Listing;
import org.tiere.dto.ListingCreation;
import org.tiere.entity.AnimalEntity;
import org.tiere.entity.BirthdayEntity;
import org.tiere.entity.FileEntity;
import org.tiere.entity.ListingEntity;
import org.tiere.mapper.ListingMapper;
import org.tiere.repo.ListingRepo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

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

    public Listing findById(int listingId) {
        return ListingMapper.map(listingRepo.findById(listingId));
    }

    @Transactional
    public void save(ListingCreation listing) throws IOException {
        List<AnimalEntity> animalEntities = listing.animals().stream().map(item -> new AnimalEntity(item.name(), new BirthdayEntity(item.birthday().year(), item.birthday().month(), item.birthday().day()))).toList();
        ListingEntity listingEntity = new ListingEntity(listing.type(), animalEntities, base64ToFile(listing.files()), authenticationService.requireAccount());
        listingRepo.persist(listingEntity);
    }

    public List<Listing> findByAccount() {
        return ListingMapper.map(listingRepo.findByAccount(authenticationService.requireAccount()));
    }

    private List<FileEntity> base64ToFile(List<String> base64) {
        return base64.stream().map(item -> base64ToFile(item)).toList();
    }

    private FileEntity base64ToFile(String base64) {
        return new FileEntity(getMimeType(base64), Base64.getMimeDecoder().decode(extractBase64(base64)));
    }

    private String getMimeType(String base64) {
        String[] fractions = base64.split(",");
        if(fractions.length > 0) {
            String fraction = fractions[0];
            if(fraction.length() > 5) {
                return fraction.substring(5, fraction.indexOf(';'));
            }
        }
        return null;
    }

    private String extractBase64(String base64) {
        String[] fractions = base64.split(",");
        if(fractions.length > 1) {
            return fractions[1];
        }
        throw new RuntimeException();
    }
}
