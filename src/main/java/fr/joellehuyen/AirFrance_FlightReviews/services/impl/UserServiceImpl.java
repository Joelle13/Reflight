package fr.joellehuyen.AirFrance_FlightReviews.services.impl;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.UserDto;
import fr.joellehuyen.AirFrance_FlightReviews.exceptions.UserNotFoundException;
import fr.joellehuyen.AirFrance_FlightReviews.models.User;
import fr.joellehuyen.AirFrance_FlightReviews.repositories.UserRepository;
import fr.joellehuyen.AirFrance_FlightReviews.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
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
        return userRepository.save(newUser);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User findById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
