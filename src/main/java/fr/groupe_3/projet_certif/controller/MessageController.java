package fr.groupe_3.projet_certif.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.groupe_3.projet_certif.entity.Message;
import fr.groupe_3.projet_certif.service.MessageService;

@RestController
@RequestMapping("tinyslack")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("messages")
    public List<Message> getMessages() {

        return messageService.getAllMessages();
    }

    @GetMapping("messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("id") UUID idMessage) {
        Optional<Message> cOptional = messageService.getOneMessageById(idMessage);

        if (cOptional.isPresent()) {

            Message message = cOptional.get();

            return ResponseEntity.ok(message);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("messages")
    public ResponseEntity<Message> saveMessage(@RequestBody Message message) {

        Message c = messageService.addMessage(message);

        return ResponseEntity.ok(c);

    }

    @DeleteMapping("messages/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("id") UUID idMessage) {

        if (messageService.getOneMessageById(idMessage).isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        messageService.deleteById(idMessage);
        return ResponseEntity.ok("message a été supprimé");

    }

    @PutMapping("messages/{id}")
    public ResponseEntity<Message> putMessage(@PathVariable("id") UUID idMessage, @RequestBody Message putMessage) {

        // id en Json et id en body
        if (!idMessage.equals(putMessage.getMessageId())) {
            return ResponseEntity.badRequest().build();

        }
        if (messageService.getOneMessageById(idMessage).isEmpty()) {
            return ResponseEntity.notFound().build();

        }

        Message updatedMessage = messageService.updatedMessage(idMessage, putMessage);
        return ResponseEntity.ok(updatedMessage);
    }

    @PatchMapping("messages/{id}")
    public ResponseEntity<Object> patchMessage(@PathVariable("id") UUID idMessage,
            @RequestBody Message patchedMessage) {

        // id en Json et id en body
        if (!idMessage.equals(patchedMessage.getMessageId())) {
            return ResponseEntity.badRequest().build();

        }
        if (messageService.getOneMessageById(idMessage).isEmpty()) {
            return ResponseEntity.notFound().build();

        }

        messageService.patchMessage(idMessage, patchedMessage);
        return ResponseEntity.ok(messageService.getOneMessageById(idMessage));

    }

}
