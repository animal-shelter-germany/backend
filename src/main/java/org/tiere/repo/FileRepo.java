package org.tiere.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.tiere.entity.FileEntity;

@ApplicationScoped
public class FileRepo implements PanacheRepositoryBase<FileEntity, Integer> {
}
