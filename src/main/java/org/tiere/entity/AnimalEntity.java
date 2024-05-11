package org.tiere.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "animal")
public class AnimalEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "animal_name")
    private String name;

    private String sex;

    private Boolean steril;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "birthday_id")
    private BirthdayEntity birthday;

    @ManyToOne
    private ListingEntity listing;

    public AnimalEntity() {
    }

    public AnimalEntity(String name, String sex, Boolean steril, BirthdayEntity birthday) {
        birthday.setAnimal(this);
        this.name = name;
        this.sex = sex;
        this.steril = steril;
        this.birthday = birthday;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getSteril() {
        return steril;
    }

    public void setSteril(Boolean steril) {
        this.steril = steril;
    }
}
