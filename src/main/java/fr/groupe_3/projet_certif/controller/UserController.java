package fr.groupe_3.projet_certif.controller;

import java.util.List;
import java.util.Optional;

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

import fr.groupe_3.projet_certif.entity.User;
import fr.groupe_3.projet_certif.service.UserService;

@RestController
@RequestMapping("tinyslack")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("users/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable("name") String userName) {
        Optional<User> userToGet = userService.getUserByUserName(userName);

        if (userToGet.isPresent()) {
            User user = userToGet.get();
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {

        User userToPost = userService.addUser(user);

        return ResponseEntity.ok(userToPost);

    }

    @DeleteMapping("users/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable("name") String userName) {

        Optional<User> userToDelete = userService.getUserByUserName(userName);

        // si l'user' n'existe pas, renvoyer une erreur "Not Found"
        if (userToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteByUserName(userName);
        return ResponseEntity.ok("L'user a bien été supprimé");

    }

    @PutMapping("users/{name}")
    public ResponseEntity<User> putUser(@PathVariable("name") String userName, @RequestBody User updatedUser) {

        Optional<User> userToPut = userService.getUserByUserName(userName);

        // si l'user n'existe pas, renvoyer une erreur "Not Found"
        if (userToPut.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si le nom en Json et le nom renvoyé par le body ne sont pas identiques,
        // renvoyer une erreur "Bad Request"
        if (!userName.equals(updatedUser.getUserName())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(updatedUser);

    }

    @PatchMapping("users/{name}")
    public ResponseEntity<Optional<User>> patchUser(@PathVariable("name") String userName,
            @RequestBody User patchedUser) {

        Optional<User> userToPatch = userService.getUserByUserName(userName);

        // si l'user n'existe pas, renvoyer une erreur "Not Found"
        if (userToPatch.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // si le nom en Json et le nom renvoyé par le body ne sont pas identiques,
        // renvoyer une erreur "Bad Request"
        if (!userName.equals(patchedUser.getUserName())) {
            return ResponseEntity.badRequest().build();
        }

        userService.patchUser(userName, patchedUser);
        return ResponseEntity.ok(userToPatch);

    }

}