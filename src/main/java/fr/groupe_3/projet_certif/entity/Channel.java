package fr.groupe_3.projet_certif.entity;

import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "channels")
public class Channel {
    // Attributs
    @Id
    @GeneratedValue
    @UuidGenerator(style = Style.AUTO)
    private UUID channelId;

    private String channelName;

    @Column(name = "description")
    private String channelDescription;

    // ajout de l'heure de création automatiquement
    @CreationTimestamp
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /*
     * @ManyToMany
     * List<User> users = new ArrayList<>();
     * 
     * public List<User> getUsers() {
     * return users;
     * }
     * 
     * public void setUsers(List<User> users) {
     * this.users = users;
     * }
     */

    // 0 = libre, 1 = verrouillé
    @Column(name = "protection")
    private Integer locked;

    // Constructeur vide
    public Channel() {
    }

    // Constructeur avec arguments
    public Channel(String channelName, String channelDescription, LocalDate creationDate, Integer locked) {
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.creationDate = creationDate;
        this.locked = locked;
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

    public User getUser() {
        return user;
    }

    public Integer getLocked() {
        return locked;
    }

    // Setters
    public void setChannelId(UUID channelId) {
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    // To string// pour permettre à l'affichage d'avoir les valeurs en string
    @Override
    public String toString() {
        return "Channel [channelId=" + channelId + ", channelName=" + channelName + ", channelDescription="
                + channelDescription + ", creationDate=" + creationDate + ", user=" + user + ", locked=" + locked + "]";
    }

    /**
     * Méthode updateNotNull pour le PATCH
     * 
     * 
     * @param channelPatch
     */
    public void updateNotNull(Channel channelPatch) {

        // channelName
        if (channelPatch.getChannelName() != null) {
            this.setChannelName(channelPatch.getChannelName());
        }

        // channelDescription
        if (channelPatch.getChannelDescription() != null) {
            this.setChannelDescription(channelPatch.getChannelDescription());
        }

        // // user
        // if (channelPatch.getUser() != null) {
        // this.setUser(channelPatch.getUser());
        // }

        // locked
        if (channelPatch.getLocked() != null) {
            this.setLocked(channelPatch.getLocked());
        }
    }
}