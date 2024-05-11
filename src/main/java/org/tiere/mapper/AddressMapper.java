package org.tiere.mapper;

import org.tiere.dto.Address;
import org.tiere.dto.Animal;
import org.tiere.entity.AddressEntity;
import org.tiere.entity.AnimalEntity;

import java.util.List;

public class AddressMapper {

    public static Address map(AddressEntity address) {
        return new Address(address.getZip(), address.getCity());
    }

}
