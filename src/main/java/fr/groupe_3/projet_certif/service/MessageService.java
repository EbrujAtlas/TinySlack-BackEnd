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

    // Get all
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * 
     * @param idMessage
     * @return
     */
    // Get one
    public Optional<Message> getOneMessageById(UUID idMessage) {
        return messageRepository.findById(idMessage);
    }

    /**
     * 
     * @param idMessage
     */
    // Delete one
    public void deleteById(UUID idMessage) {
        messageRepository.deleteById(idMessage);
    }

    // Post one
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    // Put
    public void updatedMessage(UUID idMessage, Message message) {
        messageRepository.save(message);
    }

    // Patch
    public void patchMessage(UUID idMessage, Message messagePatch) {

        Optional<Message> optional = messageRepository.findById(idMessage);

        if (optional.isPresent()) {

            Message message = optional.get();
            System.out.println(message);
            message.updateNotNull(messagePatch);
            messageRepository.save(message);

        }
    }

}
