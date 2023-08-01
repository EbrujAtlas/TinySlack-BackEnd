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

    @GetMapping("channels/{id}")
    public ResponseEntity<Channel> getChannelById(@PathVariable("id") UUID idChannel) {
        Optional<Channel> cOptional = channelService.getOneChannelById(idChannel);

        if (cOptional.isPresent()) {

            Channel channel = cOptional.get();

            return ResponseEntity.ok(channel);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("channels")
    public ResponseEntity<Channel> saveChannel(@RequestBody Channel channel) {

        Channel c = channelService.addChannel(channel);

        return ResponseEntity.ok(c);

    }

    @DeleteMapping("channels/{id}")
    public ResponseEntity<String> deleteChannel(@PathVariable("id") UUID idChannel) {

        if (channelService.getOneChannelById(idChannel).isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        channelService.deleteById(idChannel);
        return ResponseEntity.ok("channel a été supprimé");

    }

    @PutMapping("channels/{id}")
    public ResponseEntity<Channel> putChannel(@PathVariable("id") UUID idChannel, @RequestBody Channel updatedChannel) {

        // id en Json et id en body
        if (!idChannel.equals(updatedChannel.getChannelId())) {
            return ResponseEntity.badRequest().build();

        }
        if (channelService.getOneChannelById(idChannel).isEmpty()) {
            return ResponseEntity.notFound().build();

        }

        channelService.updatedChannel(idChannel, updatedChannel);
        return ResponseEntity.ok(updatedChannel);
    }

    @PatchMapping("channels/{id}")
    public ResponseEntity<Object> patchChannel(@PathVariable("id") UUID idChannel,
            @RequestBody Channel patchedChannel) {

        // id en Json et id en body
        if (!idChannel.equals(patchedChannel.getChannelId())) {
            return ResponseEntity.badRequest().build();

        }
        if (channelService.getOneChannelById(idChannel).isEmpty()) {
            return ResponseEntity.notFound().build();

        }

        channelService.patchChannel(idChannel, patchedChannel);
        return ResponseEntity.ok(channelService.getOneChannelById(idChannel));

    }

}
