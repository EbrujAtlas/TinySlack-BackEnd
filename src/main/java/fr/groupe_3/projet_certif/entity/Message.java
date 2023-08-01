package fr.groupe_3.projet_certif.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

    // Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Column(name = "message")
    private String messageContent;

    @Column(name = "message_date")
    // ajout de l'heure de création automatiquement
    @CreationTimestamp
    private LocalDateTime messageDate;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    // Constructeurs
    public Message(String messageContent, LocalDateTime messageDate, String userName, Channel channel) {
        this.messageContent = messageContent;
        this.messageDate = messageDate;
        this.userName = userName;
        this.channel = channel;
    }

    // Constructeur vide
    public Message() {
    }

    // Getters
    public Long getMessageId() {
        return messageId;
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

    public Channel getChannel() {
        return channel;
    }

    // Setters
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
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

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    // To String
    @Override
    public String toString() {
        return "Message [messageId=" + messageId + ", messageContent=" + messageContent + ", messageDate=" + messageDate
                + ", userName=" + userName + ", channel=" + channel + "]";
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

        // channel
        if (messagePatch.getChannel() != null) {
            this.setChannel(messagePatch.getChannel());
        }

    }

}
