package org.tiere.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "address")
public class AddressEntity extends PanacheEntityBase {

    @Id
    private long id;
    private String zip;
    private String city;
    @OneToMany(mappedBy = "address")
    private List<ListingEntity> listing;

    public AddressEntity() {
    }

    public AddressEntity(String zip, String city) {
        this.zip = zip;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<ListingEntity> getListing() {
        return listing;
    }

    public void setListing(List<ListingEntity> listing) {
        this.listing = listing;
    }
}
