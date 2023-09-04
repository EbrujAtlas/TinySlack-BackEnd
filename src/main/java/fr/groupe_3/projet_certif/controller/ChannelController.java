package fr.groupe_3.projet_certif.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.groupe_3.projet_certif.entity.Channel;
import fr.groupe_3.projet_certif.entity.Message;
import fr.groupe_3.projet_certif.service.ChannelService;
import fr.groupe_3.projet_certif.service.MessageService;

@RestController
// Permet de gérer le CORS
@CrossOrigin(origins = "*")
@RequestMapping("tinyslack")
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @Autowired
    MessageService messageService;

    /**
     * GET sur l'url "tinyslack/channels" pour récupérer la liste de tous les canaux
     * 
     * @return
     */
    @GetMapping("channels")
    public List<Channel> getChannels() {
        return channelService.getAllChannels();
    }

    /**
     * GET sur l'url "tinyslack/channels/{name}" pour récupérer un canal par son nom
     * 
     * @param channelName
     * @return
     */
    @GetMapping("channels/{name}")
    public ResponseEntity<Channel> getChannelByName(@PathVariable("name") String channelName) {
        Optional<Channel> channel = channelService.getOneChannelByName(channelName);

        // si le nom en url ne correspond à aucun canal, renvoie une erreur "Not Found"
        if (channel.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si le nom en url correspond à un canal existant, affiche ce canal
        Channel channelToGet = channel.get();
        return ResponseEntity.ok(channelToGet);
    }

    /**
     * GET sur l'url "tinyslack/channels/{name}/messages" pour récupérer un canal
     * par son nom
     * 
     * @param channelName
     * @return
     */
    @GetMapping("channels/{name}/messages")
    public ResponseEntity<List<Message>> getMessagesByChannelName(@PathVariable("name") String channelName) {
        Optional<Channel> channel = channelService.getOneChannelByName(channelName);

        // si le nom en url ne correspond à aucun canal, renvoie une erreur "Not Found"
        if (channel.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // on récupère le canal dont le nom est en url, puis on récupère la liste des
        // messages correspondants à ce canal
        Channel channelToGet = channel.get();
        List<Message> messagesFromChannel = messageService.getAllMessages().stream()
                .filter(x -> x.getChannel().getChannelId() == channelToGet.getChannelId())
                .collect(Collectors.toList());
        return ResponseEntity.ok(messagesFromChannel);
    }

    /**
     * POST sur l'url "tinyslack/channels" pour ajouter un nouveau canal
     * 
     * @param newChannel
     * @return
     */
    @PostMapping("channels")
    public ResponseEntity<Object> saveChannel(@RequestBody Channel newChannel) {

        Optional<Channel> channelToPost = channelService.getOneChannelByName(newChannel.getChannelName());

        // si le nom dans le corps de la requête correspond à un canal existant,
        // renvoie une erreur "Bad Request"
        if (channelToPost.isPresent()) {
            return ResponseEntity.badRequest().body("ce canal existe déjà");
        }

        // si le nom dans le corps de la requête ne correspond à aucun canal existant,
        // ajoute le nouveau canal
        Channel postChannel = channelToPost.get();
        channelService.addChannel(postChannel);
        return ResponseEntity.ok(postChannel);

    }

    /**
     * DELETE sur l'url "tinyslack/channels/{name}" pour supprimer un canal existant
     * 
     * @param channelName
     * @return
     */
    @DeleteMapping("channels/{name}")
    public ResponseEntity<Object> deleteChannel(@PathVariable("name") String channelName) {

        Optional<Channel> channelToDelete = channelService.getOneChannelByName(channelName);

        // si le canal n'existe pas, renvoie une erreur "Not Found"
        if (channelToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si le canal est vérouillé, renvoie une erreur "Bad Request"
        if (channelToDelete.get().getLocked() == 1) {
            return ResponseEntity.badRequest().body("Le canal est verouillé, suppression impossible");
        }

        // si le canal existe et n'est pas vérouillé, supprime le canal
        channelService.deleteByName(channelName);
        return ResponseEntity.ok().build();

    }

    /**
     * PUT sur l'url "tinyslack/channels/{name}" pour modifier un canal existant
     * 
     * @param channelName
     * @param modification
     * @return
     */
    @PutMapping("channels/{name}")
    public ResponseEntity<Object> putChannel(@PathVariable("name") String channelName,
            @RequestBody Channel modification) {

        // pour récupérer le canal qui correspond au "name" en URL
        Optional<Channel> channelToPut = channelService.getOneChannelByName(channelName);

        // à partir du "name" en URL, on récupère l'ID du canal correspondant
        UUID channelId = channelToPut.get().getChannelId();

        // si le canal n'existe pas, renvoie une erreur "Not Found"
        if (channelToPut.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si l'id du canal dont le nom est dans l'url et l'id' renvoyé par le corps de
        // la requête ne sont pas
        // identiques, renvoie une erreur "Bad Request"
        if (!channelId.equals(modification.getChannelId())) {
            return ResponseEntity.badRequest().body("Ce canal ne correspond pas à la modification demandée");

        }

        // si le canal est vérouillé, renvoie une erreur "Bad Request"
        if (channelToPut.get().getLocked() == 1) {
            return ResponseEntity.badRequest().body("Ce canal est vérouillé et ne peut être modifié");

        }

        // si le canal existe, que le nom en url et en corps de la requête
        // correspondent; et que le canal n'est pas vérouillé, celui-ci est modifié
        Channel updatedChannel = channelService.updatedChannel(modification);
        return ResponseEntity.ok(updatedChannel);

    }

    /**
     * PATCH sur l'url "tinyslack/channels/{name}" pour modifier un canal existant
     * 
     * @param channelName
     * @param patch
     * @return
     */
    @PatchMapping("channels/{name}")
    public ResponseEntity<Object> patchChannel(@PathVariable("name") String channelName,
            @RequestBody Channel patch) {

        Optional<Channel> channelToPatch = channelService.getOneChannelByName(channelName);

        Optional<Channel> channel = channelService.getOneChannelByName(patch.getChannelName());

        // si le canal n'existe pas, renvoie une erreur "Not Found"
        if (channelToPatch.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si l'id du canal dont le nom est dans l'url et l'id' renvoyé par le corps de
        // la requête ne sont pas identiques, renvoie une erreur "Bad Request"
        if (!channelToPatch.get().getChannelId().equals(patch.getChannelId())) {
            return ResponseEntity.badRequest().body("Ce canal ne correspond pas à la modification demadnée");

        }

        // si le canal est vérouillé, renvoie une erreur "Bad Request"
        if (channelToPatch.get().getLocked() == 1) {
            return ResponseEntity.badRequest().body("Ce canal est vérouillé et ne peut être modifié");

        }

        if (channel.isPresent()) {
            return ResponseEntity.badRequest().body("le canal existe dejà");
        }

        channelService.patchChannel(channelName, patch);
        return ResponseEntity.ok(channelToPatch);

        // channelToPatch = channelService.getOneChannelByName(channelName);
        // On ne peut pas changer le nom, preuve:ligne 157-160:

    }

}