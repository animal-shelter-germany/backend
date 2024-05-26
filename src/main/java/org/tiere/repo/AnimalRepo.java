package org.tiere.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.tiere.entity.AnimalEntity;

@ApplicationScoped
public class AnimalRepo implements PanacheRepositoryBase<AnimalEntity, Integer> {
}
