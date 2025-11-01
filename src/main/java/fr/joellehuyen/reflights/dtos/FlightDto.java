package fr.joellehuyen.reflights.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.joellehuyen.reflights.models.Airline;
import fr.joellehuyen.reflights.models.Flight;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class FlightDto {

    private String id;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime departureTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime arrivalTime;
    private Airline airline;

    public static FlightDto mapToDTO(Flight flight) {
        return FlightDto.builder()
                .id(flight.getId())
                .departureAirport(flight.getDepartureAirport())
                .arrivalAirport(flight.getArrivalAirport())
                .departureDate(flight.getDepartureDate())
                .arrivalDate(flight.getArrivalDate())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .airline(flight.getAirline())
                .build();
    }

}
