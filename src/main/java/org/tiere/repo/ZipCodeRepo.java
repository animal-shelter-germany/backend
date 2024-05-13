package org.tiere.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.tiere.entity.ZipCodeEntity;

@ApplicationScoped
public class ZipCodeRepo implements PanacheRepositoryBase<ZipCodeEntity, Integer> {

    public ZipCodeEntity findByZipCode(String zipCode) {
        return find("zipCode = :zipCode", Parameters.with("zipCode", zipCode)).firstResult();
    }

}
