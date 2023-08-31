package fr.groupe_3.projet_certif.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.groupe_3.projet_certif.dao.MessageRepository;
import fr.groupe_3.projet_certif.entity.Message;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    /**
     * Get all messages
     * 
     * @return
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * Get one By Id
     * 
     * @param messageId
     * @return
     */
    public Optional<Message> getOneMessageById(UUID messageId) {
        return messageRepository.findById(messageId);
    }

    /**
     * Delete one by Id
     * 
     * @param messageId
     */
    public void deleteById(UUID messageId) {
        messageRepository.deleteById(messageId);
    }

    /**
     * Create a new message
     * 
     * @param message
     * @return
     */
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    /**
     * Update a message
     * 
     * 
     * @param message
     * @return
     */
    public Message updatedMessage(Message message) {
        return messageRepository.save(message);
    }

    /**
     * Patch a message
     * 
     * @param messageId
     * @param messagePatch
     */
    // public void patchMessage(UUID messageId, Message messagePatch) {

    // Optional<Message> optional = messageRepository.findById(messageId);

    // if (optional.isPresent()) {

    // Message message = optional.get();
    // message.updateNotNull(messagePatch);
    // messageRepository.save(message);

    // }
    // }

    public void patchMessage(UUID messageId, Message messagePatch) {

        Optional<Message> optional = messageRepository.findById(messageId);

        if (optional.isPresent()) {

            Message message = optional.get();
            message.updateNotNull(messagePatch);
            messageRepository.save(message);

        }
    }

}