package fr.groupe_3.projet_certif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @param channelName
     * @return
     */
    // Get one
    public Optional<Channel> getOneChannelByName(String channelName) {
        return channelRepository.findByChannelName(channelName);
    }

    /**
     * 
     * @param channelName
     */
    // Delete one
    @Transactional
    public void deleteByName(String channelName) {

        channelRepository.deleteByChannelName(channelName);

    }

    // Post one
    public Channel addChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    // Put
    public void updatedChannel(String channelName, Channel channel) {
        channelRepository.save(channel);
    }

    // Patch
    public void patchChannel(String channelName, Channel channelPatch) {

        Optional<Channel> optional = channelRepository.findByChannelName(channelName);

        if (optional.isPresent()) {

            Channel channel = optional.get();
            System.out.println(channel);
            channel.updateNotNull(channelPatch);
            channelRepository.save(channel);

        }
    }

}
