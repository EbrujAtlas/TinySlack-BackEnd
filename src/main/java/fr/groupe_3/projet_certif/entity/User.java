package fr.groupe_3.projet_certif.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    // Attributs
    @Id
    @GeneratedValue
    @UuidGenerator(style = Style.AUTO)
    private UUID userId;

    private String userName;

    private String password;

    private String userMail;

    // Constructeur
    public User() {
    }

    // Getters
    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserMail() {
        return userMail;
    }

    // Setters
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    // To string
    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", userMail=" + userMail
                + "]";
    }

    /**
     * Méthode updateNotNull pour le PATCH
     * 
     * @param userPatch
     */
    public void updateNotNull(User userPatch) {

        if (userPatch.getUserName() != null) {

            this.setUserName(userPatch.getUserName());
        }

        if (userPatch.getPassword() != null) {

            this.setPassword(userPatch.getPassword());
        }

        if (userPatch.getUserMail() != null) {

            this.setUserMail(userPatch.getUserMail());
        }

    }

}