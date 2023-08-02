package fr.groupe_3.projet_certif.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.groupe_3.projet_certif.entity.Message;

public interface MessageRepository extends JpaRepository<Message, UUID> {

}