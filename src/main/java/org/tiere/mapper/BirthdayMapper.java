package org.tiere.mapper;

import org.tiere.dto.Birthday;
import org.tiere.dto.Animal;
import org.tiere.entity.AnimalEntity;
import org.tiere.entity.BirthdayEntity;

import java.util.List;

public class BirthdayMapper {

    public static Birthday map(BirthdayEntity birthday) {
        return new Birthday(birthday.getYear(), birthday.getMonth(), birthday.getDay());
    }

}
