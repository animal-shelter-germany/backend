package org.tiere.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "animal")
public class AnimalEntity extends PanacheEntity {

    @Column(name = "animal_name")
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "birthday_id")
    private BirthdayEntity birthday;
    @ManyToOne
    private ListingEntity listing;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BirthdayEntity getBirthday() {
        return birthday;
    }

    public void setBirthday(BirthdayEntity birthday) {
        this.birthday = birthday;
    }

    public ListingEntity getListing() {
        return listing;
    }

    public void setListing(ListingEntity listing) {
        this.listing = listing;
    }
}
