package org.tiere.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.tiere.entity.FileEntity;

@ApplicationScoped
public class FileRepo implements PanacheRepository<FileEntity> {
}
