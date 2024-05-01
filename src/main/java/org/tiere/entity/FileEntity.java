package org.tiere.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "file")
public class FileEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(columnDefinition = "text")
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "listing_id")
    private ListingEntity listing;

    public FileEntity() {
    }

    public FileEntity(String mimeType, byte[] content) {
        this.mimeType = mimeType;
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String extension) {
        this.mimeType = extension;
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
}
