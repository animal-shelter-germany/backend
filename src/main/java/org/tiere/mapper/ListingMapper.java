package org.tiere.mapper;

import org.tiere.dto.Listing;
import org.tiere.entity.ListingEntity;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ListingMapper {

    public static List<Listing> map(List<ListingEntity> listings) {
        return listings.stream().map(ListingMapper::map).toList();
    }

    public static Listing map(ListingEntity listing) {
        return new Listing(listing.getId(), AnimalMapper.map(listing.getAnimals()), listing.getType(), listing.getFiles().stream().map(file -> file.getId()).toList());
    }

}
