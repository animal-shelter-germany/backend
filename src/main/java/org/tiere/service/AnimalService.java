package org.tiere.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.tiere.dto.Animal;
import org.tiere.dto.AnimalCreation;
import org.tiere.dto.AnimalUpdate;
import org.tiere.entity.AnimalEntity;
import org.tiere.entity.BirthdayEntity;
import org.tiere.mapper.AnimalMapper;
import org.tiere.repo.AnimalRepo;
import org.tiere.repo.ListingRepo;

@ApplicationScoped
public class AnimalService {

    private final AnimalRepo animalRepo;
    private final ListingRepo listingRepo;

    @Inject
    public AnimalService(AnimalRepo animalRepo, ListingRepo listingRepo) {
        this.animalRepo = animalRepo;
        this.listingRepo = listingRepo;
    }

    @Transactional
    public Animal create(int listingId, AnimalCreation animal) {
        AnimalEntity entity = new AnimalEntity(animal.name(), animal.sex(), animal.steril(), new BirthdayEntity(animal.birthday().year(), animal.birthday().month(), animal.birthday().day()));
        entity.setListing(listingRepo.findById(listingId));
        entity.persistAndFlush();
        return AnimalMapper.map(entity);
    }

    @Transactional
    public void update(int id, AnimalUpdate animal) {
        AnimalEntity existing = animalRepo.findById(id);
        existing.setName(animal.name());
        existing.setSex(animal.sex());
        existing.setSteril(animal.steril());
        existing.getBirthday().setYear(animal.birthday().year());
        existing.getBirthday().setMonth(animal.birthday().month());
        existing.getBirthday().setDay(animal.birthday().day());
        animalRepo.persistAndFlush(existing);
    }

    @Transactional
    public void delete(int animalId) {
        animalRepo.deleteById(animalId);
    }
}
