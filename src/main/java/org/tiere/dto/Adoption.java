package org.tiere.dto;

import org.tiere.util.AdoptionStatus;

import java.time.LocalDateTime;
import java.util.List;

public record Adoption(Integer id, AdoptionStatus status, LocalDateTime requestedAt, List<Animal> animals) {
}
