package org.tiere.dto;

import org.tiere.util.ListingType;

import java.util.List;

public record Listing(Integer id, List<Animal> animals, ListingType type, List<Integer> files, Address address) {
}
