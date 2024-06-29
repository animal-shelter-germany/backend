package org.tiere.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "adoption_animal")
public class AdoptionAnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private AnimalEntity animal;

    @ManyToOne
    @JoinColumn(name = "adoption_id")
    private AdoptionEntity adoption;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AnimalEntity getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalEntity animal) {
        this.animal = animal;
    }

    public AdoptionEntity getAdoption() {
        return adoption;
    }

    public void setAdoption(AdoptionEntity adoption) {
        this.adoption = adoption;
    }
}
