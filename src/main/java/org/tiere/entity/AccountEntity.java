package org.tiere.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.vertx.core.eventbus.Message;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "account")
public class AccountEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "account_password")
    private String password;

    @OneToMany(mappedBy = "account")
    private List<ListingEntity> listings;

    @OneToMany(mappedBy = "sender")
    private List<MessageEntity> sentMessages;

    @OneToMany(mappedBy = "receiver")
    private List<MessageEntity> receivedMessages;

    @OneToMany(mappedBy = "requestor")
    private List<AdoptionEntity> adoptions;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ListingEntity> getListings() {
        return listings;
    }

    public void setListings(List<ListingEntity> listings) {
        this.listings = listings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MessageEntity> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<MessageEntity> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<MessageEntity> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<MessageEntity> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<AdoptionEntity> getAdoptions() {
        return adoptions;
    }

    public void setAdoptions(List<AdoptionEntity> adoptions) {
        this.adoptions = adoptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return id == that.id;
    }
}
