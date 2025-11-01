package fr.joellehuyen.reflights.services.impl;

import fr.joellehuyen.reflights.exceptions.UserNotFoundException;
import fr.joellehuyen.reflights.models.User;
import fr.joellehuyen.reflights.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldReturnAllUsers() {
        User u1 = new User(); u1.setId("1"); u1.setFirstName("John"); u1.setLastName("Doe"); u1.setEmail("john@example.com");
        User u2 = new User(); u2.setId("2"); u2.setFirstName("Jane"); u2.setLastName("Roe"); u2.setEmail("jane@example.com");

        given(userRepository.findAll()).willReturn(Arrays.asList(u1, u2));

        List<User> res = userService.getAllUsers();

        assertEquals(2, res.size());
        then(userRepository).should().findAll();
    }

    @Test
    void shouldGetUserByEmail_whenExists() {
        User u = new User(); u.setId("1"); u.setEmail("john@example.com");
        given(userRepository.findByEmail("john@example.com")).willReturn(Optional.of(u));

        User res = userService.getUserByEmail("john@example.com");

        assertEquals(u, res);
    }

    @Test
    void shouldThrow_whenGetUserByEmailNotFound() {
        given(userRepository.findByEmail("unknown@example.com")).willReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail("unknown@example.com"));
    }
}
