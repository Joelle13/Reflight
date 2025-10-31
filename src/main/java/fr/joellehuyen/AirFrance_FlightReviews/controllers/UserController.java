package fr.joellehuyen.AirFrance_FlightReviews.controllers;


import fr.joellehuyen.AirFrance_FlightReviews.dtos.UserDto;
import fr.joellehuyen.AirFrance_FlightReviews.models.User;
import fr.joellehuyen.AirFrance_FlightReviews.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @Operation (summary = "To get all users")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = users.stream()
                .map(UserDto::mapToDTO)
                .toList();
        return ResponseEntity.ok(userDtos);
    }

    @Operation (summary = "To create a new user")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(UserDto.mapToDTO(createdUser));
    }

    @Operation(summary = "Get user by email", description = "Retrieve a user by their email address")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(UserDto.mapToDTO(user));
    }
}
