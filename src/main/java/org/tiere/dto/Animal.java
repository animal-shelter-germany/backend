package org.tiere.dto;

import org.tiere.util.ListingType;

public record Animal(int id, String name, String sex, Boolean steril, Birthday birthday) {
}
