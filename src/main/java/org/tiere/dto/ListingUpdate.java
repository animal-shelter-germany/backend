package org.tiere.dto;

import org.tiere.util.ListingStatus;
import org.tiere.util.ListingType;

public record ListingUpdate(ListingType type, ListingStatus status) {
}
