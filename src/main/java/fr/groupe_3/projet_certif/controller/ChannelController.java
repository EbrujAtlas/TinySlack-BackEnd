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

//A modifier quand on fera le frontend par @Controller
@RestController
@RequestMapping("tinyslack")
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @GetMapping("channels")
    public List<Channel> getChannels() {
        return channelService.getAllChannels();
    }

    @GetMapping("channels/{name}")
    public ResponseEntity<Channel> getChannelByName(@PathVariable("name") String channelName) {
        Optional<Channel> channelToGet = channelService.getOneChannelByName(channelName);

        if (channelToGet.isPresent()) {
            Channel channel = channelToGet.get();
            return ResponseEntity.ok(channel);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("channels")
    public ResponseEntity<Channel> saveChannel(@RequestBody Channel channel) {

        Channel channelToPost = channelService.addChannel(channel);

        return ResponseEntity.ok(channelToPost);

    }

    @DeleteMapping("channels/{name}")
    public ResponseEntity<String> deleteChannel(@PathVariable("name") String channelName) {

        Optional<Channel> channelToDelete = channelService.getOneChannelByName(channelName);

        // si le canal n'existe pas, renvoyer une erreur "Not Found"
        if (channelToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si le canal est vérouillé, renvoyer une erreur "Bad Request"
        if (channelToDelete.get().getLocked() == 1) {
            return ResponseEntity.badRequest().build();
        }

        channelService.deleteByName(channelName);
        return ResponseEntity.ok("Le canal a bien été supprimé");

    }

    @PutMapping("channels/{name}")
    public ResponseEntity<Channel> putChannel(@PathVariable("name") String channelName, @RequestBody Channel updatedChannel) {

        Optional<Channel> channelToPut = channelService.getOneChannelByName(channelName);

        // si le canal n'existe pas, renvoyer une erreur "Not Found"
        if (channelToPut.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si le nom en Json et le nom renvoyé par le body ne sont pas identiques,
        // renvoyer une erreur "Bad Request"
        if (!channelName.equals(updatedChannel.getChannelName())) {
            return ResponseEntity.badRequest().build();
        }

        // si le canal est vérouillé, renvoyer une erreur "Bad Request"
        if (channelToPut.get().getLocked() == 1) {
            return ResponseEntity.badRequest().build();
        }

        channelService.updatedChannel(channelName, updatedChannel);
        return ResponseEntity.ok(updatedChannel);

    }

    @PatchMapping("channels/{name}")
    public ResponseEntity<Optional<Channel>> patchChannel(@PathVariable("name") String channelName,
            @RequestBody Channel patchedChannel) {

        Optional<Channel> channelToPatch = channelService.getOneChannelByName(channelName);

        // si le canal n'existe pas, renvoyer une erreur "Not Found"
        if (channelToPatch.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si l'id en Json et l'id renvoyé par le body ne sont pas identiques,
        // renvoyer une erreur "Bad Request"
        if (!channelName.equals(patchedChannel.getChannelName())) {
            return ResponseEntity.badRequest().build();
        }

        // si le canal est vérouillé, renvoyer une erreur "Bad Request"
        if (channelToPatch.get().getLocked() == 1) {
            return ResponseEntity.badRequest().build();
        }

        channelService.patchChannel(channelName, patchedChannel);
        return ResponseEntity.ok(channelToPatch);

    }

}