package org.tiere.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.tiere.dto.Listing;
import org.tiere.dto.ListingCreation;
import org.tiere.entity.*;
import org.tiere.mapper.ListingMapper;
import org.tiere.repo.ListingRepo;
import org.tiere.util.ListingType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
        List<AnimalEntity> animalEntities = listing.animals().
            stream().map(item -> new AnimalEntity(item.name(), item.sex(), item.steril(), new BirthdayEntity(item.birthday().year(), item.birthday().month(), item.birthday().day())))
            .toList();
        ListingEntity listingEntity = new ListingEntity(
            listing.type(),
            animalEntities,
            base64ToFile(listing.files()),
            authenticationService.requireAccount(),
            new AddressEntity(
                listing.address().zip(),
                listing.address().city())
        );
        listingRepo.persist(listingEntity);
    }

    public List<Listing> search() {
        SearchEntity search = new SearchEntity(ListingType.CAT);
        StringBuilder query = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        ListingType listingType = search.getListingType();
        if(listingType != null) {
            appendToQuery(query, params, "type",listingType);
        }

        return ListingMapper.map(listingRepo.find(query.toString(), params).list());
    }

    private void appendToQuery(StringBuilder query, Map<String, Object> params, String field, Object param) {
        if(!query.isEmpty()) {
            query.append(" and ");
        }
        query.append(field).append("=:").append(field);
        params.put(field, param);
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
