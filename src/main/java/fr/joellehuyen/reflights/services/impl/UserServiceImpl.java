package fr.joellehuyen.reflights.services.impl;

import fr.joellehuyen.reflights.dtos.UserDto;
import fr.joellehuyen.reflights.exceptions.UserNotFoundException;
import fr.joellehuyen.reflights.models.User;
import fr.joellehuyen.reflights.repositories.UserRepository;
import fr.joellehuyen.reflights.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserDto user) {
        User newUser = new User();
        System.out.println("user dto: " + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        log.info("Creating user: {} {}", user.getFirstName(), user.getLastName());
        return userRepository.save(newUser);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User with email {} not found", email);
                    return new UserNotFoundException(email);
                });
    }

    @Override
    public User findById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User with id {} not found", userId);
                    return new UserNotFoundException(userId);
                });
    }
}
