package fr.groupe_3.projet_certif.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

    // Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idMessage;

    @Column(name = "message")
    private String messageContent;

    @Column(name = "date_message")
    private LocalDateTime messageDate;

    @Column(name = "nom_utilisateur")
    private String userName;

    @ManyToOne
    @Column(name = "canal_id")
    private Canal canal;

    // Constructeurs
    public Message(String messageContent, LocalDateTime messageDate, String userName, Canal canal) {
        this.messageContent = messageContent;
        this.messageDate = messageDate;
        this.userName = userName;
        this.canal = canal;
    }

    // Constructeur vide

    public UUID getIdMessage() {
        return idMessage;
    }

    // Getters

    public Message() {
    }

    public String getMessageContent() {
        return messageContent;
    }

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public String getUserName() {
        return userName;
    }

    public Canal getCanal() {
        return canal;
    }

    // Setters
    public void setIdMessage(UUID idMessage) {
        this.idMessage = idMessage;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }

    // To String
    @Override
    public String toString() {
        return "Message [idMessage=" + idMessage + ", messageContent=" + messageContent + ", messageDate=" + messageDate
                + ", userName=" + userName + ", canal=" + canal + "]";
    }

    // Méthode updateNotNull pour le PATCH

    public void updateNotNull(Message messagePatch) {

        // messageContent
        if (messagePatch.getMessageContent() != null) {
            this.setMessageContent(messagePatch.getMessageContent());
        }

        // MessageDate
        if (messagePatch.getMessageDate() != null) {
            this.setMessageDate(messagePatch.getMessageDate());
        }

        // userName
        if (messagePatch.getUserName() != null) {
            this.setUserName(messagePatch.getUserName());
        }

        // canal
        if (messagePatch.getCanal() != null) {
            this.setCanal(messagePatch.getCanal());
        }

    }

}
