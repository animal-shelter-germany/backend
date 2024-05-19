package org.tiere.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class MessageEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private AccountEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private AccountEntity receiver;

    private String message;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    public MessageEntity() {
    }

    public MessageEntity(AccountEntity sender, AccountEntity receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.sentAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccountEntity getSender() {
        return sender;
    }

    public void setSender(AccountEntity sender) {
        this.sender = sender;
    }

    public AccountEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(AccountEntity receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
