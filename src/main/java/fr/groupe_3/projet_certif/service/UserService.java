package fr.groupe_3.projet_certif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.groupe_3.projet_certif.dao.UserRepository;
import fr.groupe_3.projet_certif.entity.User;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();

    }

    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);

    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(Long id, User putUser) {
        userRepository.save(putUser);

    }

    public void patchUser(Long id, User patchUser) {
        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()) {
            User userToPatch = optional.get();
            userToPatch.updateNotNull(patchUser);
            System.out.println(userToPatch);
            userRepository.save(userToPatch);
        }

    }

}
