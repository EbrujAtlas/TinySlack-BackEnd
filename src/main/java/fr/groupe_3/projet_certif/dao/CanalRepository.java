package fr.groupe_3.projet_certif.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.groupe_3.projet_certif.entity.Canal;

public interface CanalRepository extends JpaRepository<Canal, UUID> {

}
