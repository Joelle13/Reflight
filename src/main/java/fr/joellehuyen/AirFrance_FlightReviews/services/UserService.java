package fr.joellehuyen.AirFrance_FlightReviews.services;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.UserDto;
import fr.joellehuyen.AirFrance_FlightReviews.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User createUser(UserDto user);

    User getUserByEmail(String email);

    User findById(String userId);
}
