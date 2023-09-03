package fr.groupe_3.projet_certif.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.groupe_3.projet_certif.entity.Message;
import fr.groupe_3.projet_certif.service.MessageService;

@RestController
//Permet de gérer le CORS
@CrossOrigin(origins = "*")
@RequestMapping("tinyslack")
public class MessageController {

    @Autowired
    MessageService messageService;

    /**
     * GET sur l'url "tinyslack/messages" pour récupérer la liste de tous les
     * messages
     * 
     * @return
     */
    @GetMapping("messages")
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    /**
     * GET sur l'url "tinyslack/messages/{id}" pour récupérer un message par son id
     * 
     * @param messageId
     * @return
     */
    @GetMapping("messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("id") UUID messageId) {
        Optional<Message> message = messageService.getOneMessageById(messageId);

        // si l'id en url ne correspond à aucun message, renvoie une erreur "Not Found"
        if (message.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si l'id en url correspond à un message existant, affiche ce message
        Message messageToGet = message.get();
        return ResponseEntity.ok(messageToGet);
    }

    /**
     * POST sur l'url "tinyslack/messages" pour ajouter un nouveau message
     * 
     * @param newMessage
     * @return
     */
    @PostMapping("messages")
    public ResponseEntity<Message> saveMessage(@RequestBody Message newMessage) {

        Message messageToCreate = messageService.addMessage(newMessage);
        return ResponseEntity.ok(messageToCreate);

    }

    /**
     * DELETE sur l'url "tinyslack/messages/{id}" pour supprimer un message existant
     * 
     * @param messageId
     * @return
     */
    @DeleteMapping("messages/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("id") UUID messageId) {

        Optional<Message> messageToDelete = messageService.getOneMessageById(messageId);

        // si le message n'existe pas, renvoie une erreur "Not Found"
        if (messageToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si le message existe, supprime le message
        messageService.deleteById(messageId);
        return ResponseEntity.ok("Le message a bien été supprimé");

    }

    /**
     * PUT sur l'url "tinyslack/messages/{id}" pour modifier un message existant
     * 
     * @param messageId
     * @param modifiedMessage
     * @return
     */
    @PutMapping("messages/{id}")
    public ResponseEntity<Message> putMessage(@PathVariable("id") UUID messageId,
            @RequestBody Message modifiedMessage) {

        Optional<Message> messageToPut = messageService.getOneMessageById(messageId);

        // si le message n'existe pas, renvoie une erreur "Not Found"
        if (messageToPut.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si l'id en url et l'id renvoyé par le corps de la requête ne sont pas
        // identiques, renvoie une erreur "Bad Request"
        if (!messageId.equals(modifiedMessage.getMessageId())) {
            return ResponseEntity.badRequest().build();
        }

        // si l'id en url existe en BDD et correspond à celui renvoyé par le corps de la
        // requête, le message est modifié
        Message updatedMessage = messageService.updatedMessage(modifiedMessage);
        return ResponseEntity.ok(updatedMessage);

    }

    /**
     * PATCH sur l'url "tinyslack/messages/{id}" pour modifier un message existant
     * 
     * @param messageId
     * @param patchedMessage
     * @return
     */
    @PatchMapping("messages/{id}")
    public ResponseEntity<Object> patchMessage(@PathVariable("id") UUID messageId,
            @RequestBody Message patchedMessage) {

        Optional<Message> messageToPatch = messageService.getOneMessageById(messageId);

        // si le message n'existe pas, renvoie une erreur "Not Found"
        if (messageToPatch.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si l'id en url et l'id renvoyé par le corps de la requête ne sont pas
        // identiques, renvoie une erreur "Bad Request"
        if (!messageId.equals(patchedMessage.getMessageId())) {
            return ResponseEntity.badRequest().build();
        }

        // si l'id en url existe en BDD et correspond à celui renvoyé par le corps de la
        // requête, le message est modifié
        messageService.patchMessage(messageId, patchedMessage);
        return ResponseEntity.ok(messageToPatch);

    }

}