package com.aritra.media.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
@Entity
public class Status extends BaseEntity {
    @Lob
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @OneToOne
    @NotNull
    private Location location;
    @OneToOne
    @NotNull
    private User createdBy;
    @NotNull
    private String privacy;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    @Override
    public String toString() {
        return "Status{" +
                "description='" + description + '\'' +
                ", location=" + location +
                ", createdBy=" + createdBy +
                ", privacy='" + privacy + '\'' +
                '}';
    }
}
