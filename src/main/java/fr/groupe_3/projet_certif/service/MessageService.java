package fr.groupe_3.projet_certif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.groupe_3.projet_certif.dao.MessageRepository;
import fr.groupe_3.projet_certif.entity.Message;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    // Get all
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * 
     * @param messageId
     * @return
     */
    // Get one
    public Optional<Message> getOneMessageById(Long messageId) {
        return messageRepository.findById(messageId);
    }

    /**
     * 
     * @param messageId
     */
    // Delete one
    public void deleteById(Long messageId) {
        messageRepository.deleteById(messageId);
    }

    // Post one
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    // Put
    public void updatedMessage(Long messageId, Message message) {
        messageRepository.save(message);
    }

    // Patch
    public void patchMessage(Long messageId, Message messagePatch) {

        Optional<Message> optional = messageRepository.findById(messageId);

        if (optional.isPresent()) {

            Message message = optional.get();
            System.out.println(message);
            message.updateNotNull(messagePatch);
            messageRepository.save(message);

        }
    }

}
