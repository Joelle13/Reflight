package fr.joellehuyen.AirFrance_FlightReviews.dtos;

import fr.joellehuyen.AirFrance_FlightReviews.models.Airline;
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
