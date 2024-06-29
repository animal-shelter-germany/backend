package org.tiere.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.tiere.dto.Listing;
import org.tiere.dto.ListingCreation;
import org.tiere.dto.ListingUpdate;
import org.tiere.dto.Search;
import org.tiere.entity.*;
import org.tiere.mapper.ListingMapper;
import org.tiere.repo.ListingRepo;
import org.tiere.repo.ZipCodeRepo;
import org.tiere.util.ListingType;
import org.tiere.util.query_builder.QueryBuilder;
import org.tiere.util.query_builder.section.QueryCustomSection;
import org.tiere.util.query_builder.section.QuerySection;
import org.tiere.util.query_builder.util.Compartor;

import java.io.*;
import java.util.*;

@ApplicationScoped
public class ListingService {

    private final ListingRepo listingRepo;
    private final AuthenticationService authenticationService;
    private final ZipCodeRepo zipCodeRepo;

    @Inject
    public ListingService(ListingRepo listingRepo, AuthenticationService authenticationService, ZipCodeRepo zipCodeRepo) {
        this.listingRepo = listingRepo;
        this.authenticationService = authenticationService;
        this.zipCodeRepo = zipCodeRepo;
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

    public List<Listing> search(Search search) {
        QueryBuilder<ListingEntity> queryBuilder = QueryBuilder.build();

        ListingType listingType = search.listingType();
        if(listingType != null) {
            queryBuilder.add(QuerySection.of("type", Compartor.EQUAlS, listingType));
        }

        List<ListingEntity> result = queryBuilder.find(listingRepo).list();

        if(search.zip() != null && !search.zip().isBlank() && search.radius() != null) {
            ZipCodeEntity requestedZip = zipCodeRepo.findByZipCode(search.zip());
            List<ListingEntity> filteredByRadius = new LinkedList<>();
            for(ListingEntity listing : result) {
                ZipCodeEntity listingZip = zipCodeRepo.findByZipCode(listing.getAddress().getZip());
                if(calculateDistance(requestedZip, listingZip) < search.radius()) {
                    filteredByRadius.add(listing);
                }
            }
            return ListingMapper.map(filteredByRadius);
        }
        return ListingMapper.map(result);
    }

    public double calculateDistance(ZipCodeEntity from, ZipCodeEntity to) {
        return calculateDistance(from.getLatitude(), to.getLatitude(), from.getLongitude(), to.getLongitude());
    }

    public double calculateDistance(Double lat0, Double lat1, Double long0, Double long1) {
        double x1 = lat0*110.574;
        double x2 = lat1*110.574;
        double y1 = long0*111.195;
        double y2 = long1*111.195;
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(Math.cos(0.5 * Math.PI * (lat0/90)) * (y1 - y2), 2));
    }

    @Transactional
    public void delete(Integer listingId) {
        listingRepo.delete(listingRepo.findById(listingId));
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

    public List<Listing> findLatest(int count) {
        return ListingMapper.map(listingRepo.findLatest(count));
    }

    @Transactional
    public void update(int listingId, ListingUpdate listing) {
        ListingEntity existing = listingRepo.findById(listingId);
        existing.setType(listing.type());
        existing.setStatus(listing.status());
        listingRepo.persistAndFlush(existing);
    }
}
