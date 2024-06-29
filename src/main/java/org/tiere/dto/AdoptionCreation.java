package org.tiere.dto;

import java.util.List;

public record AdoptionCreation(Listing listing, List<Animal> animals) {

}
