package org.tiere.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.tiere.entity.AdoptionEntity;

@ApplicationScoped
public class AdoptionRepo implements PanacheRepositoryBase<AdoptionEntity, Integer> {

}
