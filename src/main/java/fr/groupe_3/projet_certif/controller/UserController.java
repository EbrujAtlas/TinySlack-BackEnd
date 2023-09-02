package fr.groupe_3.projet_certif.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.groupe_3.projet_certif.entity.User;
import fr.groupe_3.projet_certif.service.UserService;

@RestController
//Permet de gérer le CORS
@CrossOrigin(origins = "*")
@RequestMapping("tinyslack")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * GET sur l'url "tinyslack/users" pour récupérer la liste de tous les
     * utilisateurs
     * 
     * @return
     */
    @GetMapping("users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    /**
     * GET sur l'url "tinyslack/users/{name}" pour récupérer un utilisateur par son
     * nom
     * 
     * @param userName
     * @return
     */
    @GetMapping("users/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable("name") String userName) {
        Optional<User> user = userService.getUserByUserName(userName);

        // si le nom en url ne correspond à aucun utilisateur, renvoie une erreur "Not
        // Found"
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si le nom en url correspond à un utilisateur existant, affiche cet
        // utilisateur
        User userToGet = user.get();
        return ResponseEntity.ok(userToGet);
    }

    /**
     * POST sur l'url "tinyslack/users" pour ajouter un nouvel utilisateur
     * 
     * @param newUser
     * @return
     */
    @PostMapping("users")
    public ResponseEntity<String> saveUser(@RequestBody User newUser) {

        // si le nom dans le corps de la requête correspond à un utilisteur existant,
        // renvoie une erreur "Bad Request"
        if (userService.getUserByUserName(newUser.getUserName()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        // si le nom dans le corps de la requête ne correspond à aucun utilisateur
        // existant, ajoute le nouvel utilisateur
        userService.addUser(newUser);
        return ResponseEntity.ok("L'utilisateur a bien été créé");

    }

    /**
     * DELETE sur l'url "tinyslack/users/{name}" pour supprimer un utilisateur
     * existant
     * 
     * @param userName
     * @return
     */
    @DeleteMapping("users/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable("name") String userName) {

        Optional<User> userToDelete = userService.getUserByUserName(userName);

        // si l'utilisateur n'existe pas, renvoie une erreur "Not Found"
        if (userToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si l'utilisateur existe, supprime l'utilisateur
        userService.deleteByUserName(userName);
        return ResponseEntity.ok("L'utilisateur a bien été supprimé");

    }

    /**
     * PUT sur l'url "tinyslack/users/{name}" pour modifier un utilisateur existant
     * 
     * @param userName
     * @param modifiedUser
     * @return
     */
    @PutMapping("users/{name}")
    public ResponseEntity<User> putUser(@PathVariable("name") String userName, @RequestBody User modifiedUser) {

        Optional<User> userToPut = userService.getUserByUserName(userName);

        // si l'utilisateur n'existe pas, renvoie une erreur "Not Found"
        if (userToPut.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si le nom en url renvoie vers une entrée en BB dont l'id est différent de
        // celui dans le corps de la requête,
        // renvoie une erreur "Bad Request"
        if (!userToPut.get().getUserId().equals(modifiedUser.getUserId())) {
            return ResponseEntity.badRequest().build();
        }

        // si le nom en url existe en BDD et correspond à celui renvoyé par le corps de
        // la requête, l'utilisateur est modifié
        User updatedUser = userService.updateUser(modifiedUser);
        return ResponseEntity.ok(updatedUser);

    }

    /**
     * PATCH sur l'url "tinyslack/users/{name}" pour modifier un utilisateur
     * existant
     * 
     * @param userName
     * @param patchedUser
     * @return
     */
    @PatchMapping("users/{name}")
    public ResponseEntity<Optional<User>> patchUser(@PathVariable("name") String userName,
            @RequestBody User patchedUser) {

        Optional<User> userToPatch = userService.getUserByUserName(userName);

        // si l'utilisateur n'existe pas, renvoie une erreur "Not Found"
        if (userToPatch.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si le nom en url et le nom renvoyé par le corps de la requête ne sont pas
        // identiques, renvoie une erreur "Bad Request"
        if (!userName.equals(patchedUser.getUserName())) {
            return ResponseEntity.badRequest().build();
        }

        // si le nom en url existe en BDD et correspond à celui renvoyé par le corps de
        // la requête, l'utilisateur est modifié
        userService.patchUser(userName, patchedUser);
        return ResponseEntity.ok(userToPatch);

    }

}