package org.tiere.dto;

import org.tiere.util.ListingType;

import java.util.List;

public record Listing(Long id, List<Animal> animals, ListingType type, List<Long> files) {
}
