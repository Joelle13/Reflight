package fr.joellehuyen.reflights.dtos;

import fr.joellehuyen.reflights.models.Airline;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AirlineDto {
    private String id;
    private String name;
    private String url;

    public static AirlineDto mapToDTO(Airline airline) {
        return AirlineDto.builder()
                .id(airline.getId())
                .name(airline.getName())
                .url(airline.getUrl())
                .build();
    }

}
