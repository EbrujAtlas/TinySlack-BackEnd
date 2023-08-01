package fr.groupe_3.projet_certif.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.groupe_3.projet_certif.dao.ChannelRepository;
import fr.groupe_3.projet_certif.entity.Channel;

@Service
public class ChannelService {

    @Autowired
    ChannelRepository channelRepository;

    // Get all
    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    /**
     * 
     * @param idChannel
     * @return
     */
    // Get one
    public Optional<Channel> getOneChannelById(UUID idChannel) {
        return channelRepository.findById(idChannel);
    }

    /**
     * 
     * @param idChannel
     */
    // Delete one
    public void deleteById(UUID idChannel) {
        channelRepository.deleteById(idChannel);
    }

    // Post one
    public Channel addChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    // Put
    public void updatedChannel(UUID idChannel, Channel channel) {
        channelRepository.save(channel);
    }

    // Patch
    public void patchChannel(UUID idChannel, Channel channelPatch) {

        Optional<Channel> optional = channelRepository.findById(idChannel);

        if (optional.isPresent()) {

            Channel channel = optional.get();
            System.out.println(channel);
            channel.updateNotNull(channelPatch);
            channelRepository.save(channel);

        }
    }

}
