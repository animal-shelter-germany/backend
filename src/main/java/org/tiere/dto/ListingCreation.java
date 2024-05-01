package org.tiere.dto;

import org.tiere.util.ListingType;

import java.nio.file.Files;
import java.util.List;

public record ListingCreation(ListingType type, List<AnimalCreation> animals, List<String> files) {
}
