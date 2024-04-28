package org.tiere.mapper;

import org.tiere.dto.Animal;
import org.tiere.entity.AnimalEntity;

import java.util.List;

public class AnimalMapper {

    public static List<Animal> map(List<AnimalEntity> animals) {
        return animals.stream().map(AnimalMapper::map).toList();
    }

    public static Animal map(AnimalEntity animal) {
        return new Animal(animal.getName());
    }

}
