package fr.groupe_3.projet_certif.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.groupe_3.projet_certif.entity.Canal;
import fr.groupe_3.projet_certif.service.CanalService;

//A modifier quand on fera le frontend par @Controller
@RestController
@RequestMapping("tinyslack")
public class CanalController {

    @Autowired
    CanalService canalService;

    @GetMapping("canals")
    public List<Canal> getCanals() {

        return canalService.getAllCanals();
    }

    @GetMapping("canals/{id}")
    public ResponseEntity<Canal> getCanalById(@PathVariable("id") UUID idCanal) {
        Optional<Canal> cOptional = canalService.getOneCanalById(idCanal);

        if (cOptional.isPresent()) {

            Canal canal = cOptional.get();

            return ResponseEntity.ok(canal);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("canals")
    public ResponseEntity<Canal> saveCanal(@RequestBody Canal canal) {

        Canal c = canalService.addCanal(canal);

        return ResponseEntity.ok(c);

    }

    @DeleteMapping("canals/{id}")
    public ResponseEntity<String> deleteCanal(@PathVariable("id") UUID idCanal) {

        if (canalService.getOneCanalById(idCanal).isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        canalService.deleteById(idCanal);
        return ResponseEntity.ok("canal a été supprimé");

    }

    @PutMapping("canals/{id}")
    public ResponseEntity<Canal> putCanal(@PathVariable("id") UUID idCanal, @RequestBody Canal updatedCanal) {

        // id en Json et id en body
        if (!idCanal.equals(updatedCanal.getIdCanal())) {
            return ResponseEntity.badRequest().build();

        }
        if (canalService.getOneCanalById(idCanal).isEmpty()) {
            return ResponseEntity.notFound().build();

        }

        canalService.updatedCanal(idCanal, updatedCanal);
        return ResponseEntity.ok(updatedCanal);
    }

    @PatchMapping("canals/{id}")
    public ResponseEntity<Object> patchCanal(@PathVariable("id") UUID idCanal, @RequestBody Canal patchedCanal) {

        // id en Json et id en body
        if (!idCanal.equals(patchedCanal.getIdCanal())) {
            return ResponseEntity.badRequest().build();

        }
        if (canalService.getOneCanalById(idCanal).isEmpty()) {
            return ResponseEntity.notFound().build();

        }

        canalService.patchCanal(idCanal, patchedCanal);
        return ResponseEntity.ok(canalService.getOneCanalById(idCanal));

    }

}
