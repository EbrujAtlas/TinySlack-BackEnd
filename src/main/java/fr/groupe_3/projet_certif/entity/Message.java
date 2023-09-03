package fr.groupe_3.projet_certif.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

    // Attributs
    @Id
    @GeneratedValue
    @UuidGenerator(style = Style.AUTO)
    private UUID messageId;

    @Column(name = "message")
    private String messageContent;

    // ajout de l'heure de création automatiquement
    @CreationTimestamp
    private LocalDateTime messageDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    // Constructeur vide
    public Message() {
    }

    // Constructeur avec arguments
    public Message(String messageContent, LocalDateTime messageDate, User user, Channel channel) {
        this.messageContent = messageContent;
        this.messageDate = messageDate;
        this.user = user;
        this.channel = channel;
    }

    // Getters
    public UUID getMessageId() {
        return messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public User getUser() {
        return user;
    }

    public Channel getChannel() {
        return channel;
    }

    // Setters
    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    // To String
    @Override
    public String toString() {
        return "Message [messageId=" + messageId + ", messageContent=" + messageContent + ", messageDate=" + messageDate
                + ", user=" + user + ", channel=" + channel + "]";
    }

    /**
     * Méthode updateNotNull pour le PATCH
     * 
     * @param messagePatch
     */
    public void updateNotNull(Message messagePatch) {

        // messageContent
        if (messagePatch.getMessageContent() != null) {
            this.setMessageContent(messagePatch.getMessageContent());
        }

        // MessageDate
        if (messagePatch.getMessageDate() != null) {
            this.setMessageDate(messagePatch.getMessageDate());
        }

        // user
        if (messagePatch.getUser() != null) {
            this.setUser(messagePatch.getUser());
        }

        // channel
        if (messagePatch.getChannel() != null) {
            this.setChannel(messagePatch.getChannel());
        }

    }

}