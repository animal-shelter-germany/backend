package org.tiere.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.tiere.util.ListingType;

import java.util.List;

@Entity
@Table(name = "listing")
public class ListingEntity extends PanacheEntity {

    @Enumerated(EnumType.STRING)
    private ListingType type;

    @OneToMany(mappedBy = "listing")
    private List<AnimalEntity> animals;

    @OneToMany(mappedBy = "listing", fetch = FetchType.LAZY)
    private List<FileEntity> files;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

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
}
