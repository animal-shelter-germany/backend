package org.tiere;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.tiere.repo.ZipCodeRepo;
import org.tiere.service.ListingService;

@QuarkusTest
public class ListingServiceTest {

    private final ZipCodeRepo zipCodeRepo;
    private final ListingService listingService;

    @Inject
    public ListingServiceTest(ZipCodeRepo zipCodeRepo, ListingService listingService) {
        this.zipCodeRepo = zipCodeRepo;
        this.listingService = listingService;
    }

    @Test
    public void testCalculateDistance() {
        //Ernsgaden - Geisenfeld: Expected 6,0 KM
        //final double dis2 = listingService.calculateDistance(48.73233908617106, 48.68387488667959, 11.575918580973534, 11.611662777514791);
        final double dis2 = listingService.calculateDistance(zipCodeRepo.findByZipCode("85119"), zipCodeRepo.findByZipCode("85290"));
        System.out.println(dis2);
    }

}
