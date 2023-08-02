package fr.groupe_3.projet_certif.service;

import java.util.List;
import java.util.Optional;

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
     * @param channelId
     * @return
     */
    // Get one
    public Optional<Channel> getOneChannelById(Long channelId) {
        return channelRepository.findById(channelId);
    }

    /**
     * 
     * @param channelId
     */
    // Delete one
    public void deleteById(Long channelId) {

        channelRepository.deleteById(channelId);

    }

    // Post one
    public Channel addChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    // Put
    public void updatedChannel(Long channelId, Channel channel) {
        channelRepository.save(channel);
    }

    // Patch
    public void patchChannel(Long channelId, Channel channelPatch) {

        Optional<Channel> optional = channelRepository.findById(channelId);

        if (optional.isPresent()) {

            Channel channel = optional.get();
            System.out.println(channel);
            channel.updateNotNull(channelPatch);
            channelRepository.save(channel);

        }
    }

}
