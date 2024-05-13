package org.tiere.dto;

import org.tiere.util.ListingType;

public record Search(ListingType listingType, Boolean steril, Integer radius, String zip) {
}
