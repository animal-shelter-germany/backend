package org.tiere.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.tiere.dto.Listing;

import java.util.List;

@Entity
@Table(name = "file")
public class FileEntity extends PanacheEntity {

    private String extension;

    @Column(columnDefinition = "text")
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "listing_id")
    private ListingEntity listing;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public ListingEntity getListing() {
        return listing;
    }

    public void setListing(ListingEntity listing) {
        this.listing = listing;
    }
}
