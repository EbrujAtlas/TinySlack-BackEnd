package fr.groupe_3.projet_certif.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
     * Get one channel by Id
     * 
     * @param channelId
     * @return
     */
    public Optional<Channel> getOneChannelById(UUID channelId) {
        return channelRepository.findById(channelId);
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
     * @param channelId
     * @param channelPatch
     */
    public void patchChannel(UUID channelId, Channel channelPatch) {

        Optional<Channel> optional = channelRepository.findById(channelId);

        if (optional.isPresent()) {

            Channel channel = optional.get();
            channel.updateNotNull(channelPatch);
            channelRepository.save(channel);

        }
    }

}