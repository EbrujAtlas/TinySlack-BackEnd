package fr.groupe_3.projet_certif.controller;

import java.util.List;
import java.util.Optional;

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

import fr.groupe_3.projet_certif.entity.Channel;
import fr.groupe_3.projet_certif.service.ChannelService;

@RestController
@RequestMapping("tinyslack")
public class ChannelController {

    @Autowired
    ChannelService channelService;

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
     * POST sur l'url "tinyslack/channels" pour ajouter un nouveau canal
     * 
     * @param channel
     * @return
     */
    @PostMapping("channels")
    public ResponseEntity<Channel> saveChannel(@RequestBody Channel channel) {

        // si le nom en url correspond à un canal existant, renvoie une erreur "Bad
        // Request"
        if (channelService.getOneChannelByName(channel.getChannelName()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        // si le nom en url ne correspond à aucun canal existant, ajoute le nouveau
        // canal
        Channel channelToPost = channelService.addChannel(channel);
        return ResponseEntity.ok(channelToPost);

    }

    /**
     * DELETE sur l'url "tinyslack/channels/{name}" pour supprimer un canal existant
     * 
     * @param channelName
     * @return
     */
    @DeleteMapping("channels/{name}")
    public ResponseEntity<String> deleteChannel(@PathVariable("name") String channelName) {

        Optional<Channel> channelToDelete = channelService.getOneChannelByName(channelName);

        // si le canal n'existe pas, renvoie une erreur "Not Found"
        if (channelToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si le canal est vérouillé, renvoie une erreur "Bad Request"
        if (channelToDelete.get().getLocked() == 1) {
            return ResponseEntity.badRequest().build();
        }

        // si le canal existe et n'est pas vérouillé, supprime le canal
        channelService.deleteByName(channelName);
        return ResponseEntity.ok("Le canal a bien été supprimé");

    }

    /**
     * PUT sur l'url "tinyslack/channels/{name}" pour modifier un canal existant
     * 
     * @param channelName
     * @param putChannel
     * @return
     */
    @PutMapping("channels/{name}")
    public ResponseEntity<Channel> putChannel(@PathVariable("name") String channelName,
            @RequestBody Channel putChannel) {

        Optional<Channel> channelToPut = channelService.getOneChannelByName(channelName);

        // si le canal n'existe pas, renvoie une erreur "Not Found"
        if (channelToPut.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si le nom en url et le nom renvoyé par le body ne sont pas identiques,
        // renvoie une erreur "Bad Request"
        if (!channelName.equals(putChannel.getChannelName())) {
            return ResponseEntity.badRequest().build();
        }

        // si le canal est vérouillé, renvoie une erreur "Bad Request"
        if (channelToPut.get().getLocked() == 1) {
            return ResponseEntity.badRequest().build();
        }

        // si le canal existe, que le nom en url et en body correspondent, et que le
        // canal n'est pas vérouillé, celui-ci est modifié
        Channel updatedChannel = channelService.updatedChannel(channelName, putChannel);
        return ResponseEntity.ok(updatedChannel);

    }

    /**
     * PATCH sur l'url "tinyslack/channels/{name}" pour modifier un canal existant
     * 
     * @param channelName
     * @param patchChannel
     * @return
     */
    @PatchMapping("channels/{name}")
    public ResponseEntity<Optional<Channel>> patchChannel(@PathVariable("name") String channelName,
            @RequestBody Channel patchChannel) {

        Optional<Channel> channelToPatch = channelService.getOneChannelByName(channelName);

        // si le canal n'existe pas, renvoyer une erreur "Not Found"
        if (channelToPatch.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si l'id en Json et l'id renvoyé par le body ne sont pas identiques,
        // renvoyer une erreur "Bad Request"
        if (!channelName.equals(patchChannel.getChannelName())) {
            return ResponseEntity.badRequest().build();
        }

        // si le canal est vérouillé, renvoyer une erreur "Bad Request"
        if (channelToPatch.get().getLocked() == 1) {
            return ResponseEntity.badRequest().build();
        }

        channelService.patchChannel(channelName, patchChannel);
        return ResponseEntity.ok(channelToPatch);

    }

}