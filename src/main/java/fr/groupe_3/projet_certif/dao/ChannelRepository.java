package fr.groupe_3.projet_certif.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.groupe_3.projet_certif.entity.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

}
