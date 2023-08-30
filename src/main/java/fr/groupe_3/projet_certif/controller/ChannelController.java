package fr.groupe_3.projet_certif.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.notFound().header("Erreur", "Aucun canal trouvé").build();
        }

        // si le nom en url correspond à un canal existant, affiche ce canal
        Channel channelToGet = channel.get();
        return ResponseEntity.ok(channelToGet);
    }

    /**
     * POST sur l'url "tinyslack/channels" pour ajouter un nouveau canal
     * 
     * @param newChannel
     * @return
     */
    @PostMapping("channels")
    public ResponseEntity<Object> saveChannel(@RequestBody Channel newChannel) {

        // si le nom dans le corps de la requête correspond à un canal existant,
        // renvoie une erreur "Bad Request"
        if (channelService.getOneChannelByName(newChannel.getChannelName()).isPresent()) {
            return ResponseEntity.badRequest().header("Erreur", "Ce canal existe déjà").build();
        }

        // si le nom dans le corps de la requête ne correspond à aucun canal existant,
        // ajoute le nouveau canal
        Channel channelToCreate = channelService.addChannel(newChannel);
        return ResponseEntity.ok(channelToCreate);

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
            return ResponseEntity.notFound().header("Erreur", "Aucun canal trouvé").build();
        }

        // si le canal est vérouillé, renvoie une erreur "Bad Request"
        if (channelToDelete.get().getLocked() == 1) {
            return ResponseEntity.badRequest().header("Erreur", "Ce canal est vérouillé et ne peut être supprimé")
                    .build();
        }

        // si le canal existe et n'est pas vérouillé, supprime le canal
        channelService.deleteByName(channelName);
        return ResponseEntity.ok("Le canal a bien été supprimé");

    }

    /**
     * PUT sur l'url "tinyslack/channels/{name}" pour modifier un canal existant
     * 
     * @param channelName
     * @param modifiedChannel
     * @return
     */
    @PutMapping("channels/{name}")
    public ResponseEntity<Object> putChannel(@PathVariable("name") String channelName,
            @RequestBody Channel modifiedChannel) {

        Optional<Channel> channelToPut = channelService.getOneChannelByName(channelName);

        // si le canal n'existe pas, renvoie une erreur "Not Found"
        if (channelToPut.isEmpty()) {
            return ResponseEntity.notFound().header("Erreur", "Aucun canal trouvé").build();
        }

        // si le nom en url et le nom renvoyé par le corps de la requête ne sont pas
        // identiques, renvoie une erreur "Bad Request"
        if (!channelName.equals(modifiedChannel.getChannelName())) {
            return ResponseEntity.badRequest().header("Erreur", "Ce canal ne correspond pas à la modification demandée")
                    .build();
        }

        // si le canal est vérouillé, renvoie une erreur "Bad Request"
        if (channelToPut.get().getLocked() == 1) {
            return ResponseEntity.badRequest().header("Erreur", "Ce canal est vérouillé et ne peut être modifié")
                    .build();
        }

        // si le canal existe, que le nom en url et en corps de la requête
        // correspondent; et que le canal n'est pas vérouillé, celui-ci est modifié
        Channel updatedChannel = channelService.updatedChannel(modifiedChannel);
        return ResponseEntity.ok(updatedChannel);

    }

    /**
     * PATCH sur l'url "tinyslack/channels/{name}" pour modifier un canal existant
     * 
     * @param channelName
     * @param patchedChannel
     * @return
     */
    @PatchMapping("channels/{name}")
    public ResponseEntity<Object> patchChannel(@PathVariable("name") String channelName,
            @RequestBody Channel patchedChannel) {

        Optional<Channel> channelToPatch = channelService.getOneChannelByName(channelName);

        // si le canal n'existe pas, renvoie une erreur "Not Found"
        if (channelToPatch.isEmpty()) {
            return ResponseEntity.notFound().header("Erreur", "Aucun canal trouvé").build();
        }

        // si le nom en url et le nom renvoyé par le corps de la requête ne sont pas
        // identiques, renvoie une erreur "Bad Request"
        if (!channelName.equals(patchedChannel.getChannelName())) {
            return ResponseEntity.badRequest().header("Erreur", "Ce canal ne correspond pas à la modification demandée")
                    .build();
        }

        // si le canal est vérouillé, renvoie une erreur "Bad Request"
        if (channelToPatch.get().getLocked() == 1) {
            return ResponseEntity.badRequest().header("Erreur", "Ce canal est vérouillé et ne peut être modifié")
                    .build();
        }

        channelService.patchChannel(channelName, patchedChannel);
        return ResponseEntity.ok(channelToPatch);

    }

}