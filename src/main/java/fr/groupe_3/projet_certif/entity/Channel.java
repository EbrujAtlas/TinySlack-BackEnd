package fr.groupe_3.projet_certif.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "channels")
public class Channel {

    // Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID channelId;

    @Column(name = "channel_name")
    private String channelName;

    @Column(name = "description")
    private String channelDescription;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    // 0 = libre, 1 = verrouillé
    @Column(name = "protection")
    private Integer locked;

    // Contructeur
    public Channel(String channelName, String channelDescription, LocalDate creationDate, Integer locked) {
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.creationDate = creationDate;
        this.locked = locked;
    }

    // Constructeur vide
    public Channel() {
    }

    // Getters
    public UUID getChannelId() {
        return channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Integer getLocked() {
        return locked;
    }

    // Setters
    public void setChannellId(UUID channelId) {
        this.channelId = channelId;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    // To string
    @Override
    public String toString() {
        return "Canal [channelId=" + channelId + ", channelName=" + channelName + ", channelDescription="
                + channelDescription
                + ", creationDate=" + creationDate + ", locked=" + locked + "]";
    }

    // Méthode updateNotNull pour le PATCH
    public void updateNotNull(Channel channelPatch) {

        // channelName
        if (channelPatch.getChannelName() != null) {
            this.setChannelName(channelPatch.getChannelName());
        }

        // channelDescription
        if (channelPatch.getChannelDescription() != null) {
            this.setChannelDescription(channelPatch.getChannelDescription());
        }

        // dateCreation
        if (channelPatch.getCreationDate() != null) {
            this.setCreationDate(channelPatch.getCreationDate());
        }

        // locked
        if (channelPatch.getLocked() != null) {
            this.setLocked(channelPatch.getLocked());
        }
    }
}