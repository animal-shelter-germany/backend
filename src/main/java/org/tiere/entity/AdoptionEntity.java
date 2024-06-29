package org.tiere.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.tiere.util.AdoptionStatus;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "adoption")
public class AdoptionEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;

    @OneToMany(mappedBy = "adoption", cascade = CascadeType.ALL)
    private List<AdoptionAnimalEntity> adoptionAnimal;

    @ManyToOne
    @JoinColumn(name = "requestor_id")
    private AccountEntity requestor;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    @ManyToOne
    @JoinColumn(name = "listing_id")
    private ListingEntity listing;

    public AdoptionEntity() {
    }

    public AdoptionEntity(ListingEntity listing, AdoptionStatus status, AccountEntity requestor) {
        this.listing = listing;
        this.status = status;
        this.requestor = requestor;
        this.requestedAt = LocalDateTime.now();
        this.adoptionAnimal = new LinkedList<>();
    }

    public void addAdoptionAnimal(AdoptionAnimalEntity adoptionAnimal) {
        adoptionAnimal.setAdoption(this);
        this.adoptionAnimal.add(adoptionAnimal);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AdoptionStatus getStatus() {
        return status;
    }

    public void setStatus(AdoptionStatus status) {
        this.status = status;
    }

    public List<AdoptionAnimalEntity> getAdoptionAnimal() {
        return adoptionAnimal;
    }

    public void setAdoptionAnimal(List<AdoptionAnimalEntity> adoptionAnimal) {
        this.adoptionAnimal = adoptionAnimal;
    }

    public AccountEntity getRequestor() {
        return requestor;
    }

    public void setRequestor(AccountEntity requestor) {
        this.requestor = requestor;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public ListingEntity getListing() {
        return listing;
    }

    public void setListing(ListingEntity listing) {
        this.listing = listing;
    }
}
