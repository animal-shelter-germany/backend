package org.tiere.entity;

import org.tiere.util.ListingType;

public class SearchEntity {

    private ListingType listingType;

    public SearchEntity(ListingType listingType) {
        this.listingType = listingType;
    }

    public ListingType getListingType() {
        return listingType;
    }

    public void setListingType(ListingType listingType) {
        this.listingType = listingType;
    }
}
