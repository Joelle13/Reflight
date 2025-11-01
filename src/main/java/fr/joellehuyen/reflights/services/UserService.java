package fr.joellehuyen.reflights.services;

import fr.joellehuyen.reflights.dtos.UserDto;
import fr.joellehuyen.reflights.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(UserDto user);

    User getUserByEmail(String email);

    User findById(String userId);
}
