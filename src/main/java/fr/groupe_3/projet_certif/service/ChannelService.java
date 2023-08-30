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

    /**
     * Get all channels
     * 
     * @return
     */
    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    /**
     * Get one channel by name
     * 
     * @param channelName
     * @return
     */
    public Optional<Channel> getOneChannelByName(String channelName) {
        return channelRepository.findByChannelName(channelName);
    }

    /**
     * Delete a channel by name
     * 
     * @param channelName
     */
    @Transactional
    public void deleteByName(String channelName) {
        channelRepository.deleteByChannelName(channelName);
    }

    /**
     * Create a new channel
     * 
     * @param channel
     * @return
     */
    public Channel addChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    /**
     * Update a channel
     * 
     *
     * @param channel
     * @return
     */
    public Channel updatedChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    /**
     * Patch a channel
     * 
     * @param channelName
     * @param channelPatch
     */
    public void patchChannel(String channelName, Channel channelPatch) {

        Optional<Channel> optional = channelRepository.findByChannelName(channelName);

        if (optional.isPresent()) {

            Channel channel = optional.get();
            channel.updateNotNull(channelPatch);
            channelRepository.save(channel);

        }
    }

}