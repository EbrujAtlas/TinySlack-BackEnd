package fr.groupe_3.projet_certif.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.groupe_3.projet_certif.dao.CanalRepository;
import fr.groupe_3.projet_certif.entity.Canal;

@Service
public class CanalService {

    @Autowired
    CanalRepository canalRepository;

    // Get all
    public List<Canal> getAllCanals() {
        return canalRepository.findAll();
    }

    /**
     * 
     * @param idCanal
     * @return
     */
    // Get one
    public Optional<Canal> getOneCanalById(UUID idCanal) {
        return canalRepository.findById(idCanal);
    }

    /**
     * 
     * @param idCanal
     */
    // Delete one
    public void deleteById(UUID idCanal) {
        canalRepository.deleteById(idCanal);
    }

    // Post one
    public Canal addCanal(Canal canal) {
        return canalRepository.save(canal);
    }

    // Put
    public void updatedCanal(UUID idCanal, Canal canal) {
        canalRepository.save(canal);
    }

    // Patch
    public void patchCanal(UUID idCanal, Canal canalPatch) {

        Optional<Canal> optional = canalRepository.findById(idCanal);

        if (optional.isPresent()) {

            Canal canal = optional.get();
            System.out.println(canal);
            canal.updateNotNull(canalPatch);
            canalRepository.save(canal);

        }
    }

}
