package fr.joellehuyen.AirFrance_FlightReviews.dtos;

import fr.joellehuyen.AirFrance_FlightReviews.models.Airline;
import fr.joellehuyen.AirFrance_FlightReviews.models.Flight;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FlightDto {

    private String id;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String airlineName;

    public static FlightDto mapToDTO(Flight flight) {
        return FlightDto.builder()
                .id(flight.getId())
                .departureAirport(flight.getDepartureAirport())
                .arrivalAirport(flight.getArrivalAirport())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .airlineName(flight.getAirline().getName())
                .build();
    }

    public static Flight mapToEntity(FlightDto flightDto, Airline airline) {
        Flight flight = new Flight();
        flight.setId(flightDto.getId());
        flight.setDepartureAirport(flightDto.getDepartureAirport());
        flight.setArrivalAirport(flightDto.getArrivalAirport());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setArrivalTime(flightDto.getArrivalTime());
        flight.setAirline(airline);
        return flight;
    }
}
