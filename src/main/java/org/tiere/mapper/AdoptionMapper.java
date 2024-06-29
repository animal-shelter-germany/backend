package org.tiere.mapper;

import org.tiere.dto.Adoption;
import org.tiere.dto.Animal;
import org.tiere.entity.AdoptionEntity;
import org.tiere.entity.AnimalEntity;

import java.util.List;

public class AdoptionMapper {

    public static List<Adoption> map(List<AdoptionEntity> adoptions) {
        return adoptions.stream().map(AdoptionMapper::map).toList();
    }

    public static Adoption map(AdoptionEntity adoption) {
        return new Adoption(adoption.getId(), adoption.getStatus(), adoption.getRequestedAt(), AnimalMapper.map(adoption.getAdoptionAnimal().stream().map(item -> item.getAnimal()).toList()));
    }

}
