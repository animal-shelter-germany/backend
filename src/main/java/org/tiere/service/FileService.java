package org.tiere.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tiere.dto.FileDetails;
import org.tiere.entity.FileEntity;
import org.tiere.repo.FileRepo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@ApplicationScoped
public class FileService {

    private final FileRepo fileRepo;

    @Inject
    public FileService(FileRepo fileRepo) {
        this.fileRepo = fileRepo;
    }

    public FileDetails findById(long id) throws IOException {
        FileEntity fileEntity = fileRepo.findById(id);
        File file = new File(UUID.randomUUID().toString());
        try(FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(fileEntity.getContent());
        }
        return new FileDetails(fileEntity.getExtension(), file);
    }

}
