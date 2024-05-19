package org.tiere.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.tiere.util.ListingStatus;
import org.tiere.util.ListingType;

import java.util.List;

@Entity
@Table(name = "listing")
public class ListingEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ListingType type;

    @Enumerated(EnumType.STRING)
    private ListingStatus status;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    private List<AnimalEntity> animals;

    @OneToMany(mappedBy = "listing", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FileEntity> files;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    public ListingEntity() {
    }

    public ListingEntity(ListingType type, List<AnimalEntity> animals, List<FileEntity> files, AccountEntity account, AddressEntity address) {
        animals.forEach(item -> item.setListing(this));
        files.forEach(item -> item.setListing(this));
        this.type = type;
        this.animals = animals;
        this.files = files;
        this.account = account;
        this.address = address;
    }

    public List<AnimalEntity> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalEntity> animals) {
        this.animals = animals;
    }

    public ListingType getType() {
        return type;
    }

    public void setType(ListingType type) {
        this.type = type;
    }

    public ListingStatus getStatus() {
        return status;
    }

    public void setStatus(ListingStatus status) {
        this.status = status;
    }

    public List<FileEntity> getFiles() {
        return files;
    }

    public void setFiles(List<FileEntity> files) {
        this.files = files;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
