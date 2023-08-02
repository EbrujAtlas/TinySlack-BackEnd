package fr.groupe_3.projet_certif.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.groupe_3.projet_certif.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUserName(String channelName);

    void deleteByUserName(String channelName);

}