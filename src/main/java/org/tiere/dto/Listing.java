package org.tiere.dto;

import org.tiere.util.ListingStatus;
import org.tiere.util.ListingType;

import java.util.List;

public record Listing(Integer id, List<Animal> animals, ListingType type, ListingStatus status, List<Integer> files, Address address) {
}
