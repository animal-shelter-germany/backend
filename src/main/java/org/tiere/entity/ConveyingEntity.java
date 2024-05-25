package org.tiere.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.tiere.dto.Listing;

import java.time.LocalDate;

@Entity
@Table(name = "conveying")
public class ConveyingEntity extends PanacheEntityBase {

    @Id
    private int id;

    private String status;

    private LocalDate startedAt;

    @ManyToOne
    private ListingEntity listing;

    @ManyToOne
    private AccountEntity opponent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDate startedAt) {
        this.startedAt = startedAt;
    }

    public ListingEntity getListing() {
        return listing;
    }

    public void setListing(ListingEntity listing) {
        this.listing = listing;
    }

    public AccountEntity getOpponent() {
        return opponent;
    }

    public void setOpponent(AccountEntity opponent) {
        this.opponent = opponent;
    }
}
