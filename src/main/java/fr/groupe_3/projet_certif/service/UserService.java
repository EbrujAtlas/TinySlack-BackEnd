package fr.groupe_3.projet_certif.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.groupe_3.projet_certif.dao.UserRepository;
import fr.groupe_3.projet_certif.entity.User;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Get all users
     * 
     * @return
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get one by name
     * 
     * @param userName
     * @return
     */
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public Optional<User> getUserByUserId(UUID userId) {
        return userRepository.findById(userId);
    }

    /**
     * Create a new User
     * 
     * @param user
     * @return
     */
    public User addUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Delete one by name
     * 
     * @param userName
     */
    @Transactional
    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }

    /**
     * Update an user
     * 
     * 
     * @param putUser
     * @return
     */
    public User updateUser(User putUser) {
        return userRepository.save(putUser);
    }

    /**
     * Patch an user
     * 
     * @param userName
     * @param patchUser
     */
    public void patchUser(UUID userId, User patchUser) {

        Optional<User> optional = userRepository.findById(userId);

        if (optional.isPresent()) {

            User userToPatch = optional.get();
            userToPatch.updateNotNull(patchUser);
            userRepository.save(userToPatch);

        }

    }

}