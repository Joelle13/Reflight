package fr.joellehuyen.reflights.dtos;

import fr.joellehuyen.reflights.models.User;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public static UserDto mapToDTO(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

}
