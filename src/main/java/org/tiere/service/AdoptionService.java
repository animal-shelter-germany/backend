package org.tiere.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tiere.dto.Adoption;
import org.tiere.mapper.AdoptionMapper;
import org.tiere.repo.AdoptionRepo;

import java.util.List;

@ApplicationScoped
public class AdoptionService {

    private final AuthenticationService authenticationService;

    @Inject
    public AdoptionService(AdoptionRepo adoptionRepo, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public List<Adoption> findByUser() {
        return AdoptionMapper.map(authenticationService.requireAccount().getAdoptions());
    }

}
