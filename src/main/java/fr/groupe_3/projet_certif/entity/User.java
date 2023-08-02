package fr.groupe_3.projet_certif.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String password;

    private String userMail;

    public User() {
    }

    public Long getUserId() {
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

    public void setUserId(Long userId) {
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

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", userMail=" + userMail
                + "]";
    }

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
