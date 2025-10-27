package fr.joellehuyen.AirFrance_FlightReviews.services;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.FlightDto;
import fr.joellehuyen.AirFrance_FlightReviews.models.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    List<Flight> findAll();

    Flight createFlight(FlightDto flightDto);

    List<FlightDto> getByDate(LocalDate date);

    List<FlightDto> searchFlights(LocalDate date, String airline, String number);

    List<FlightDto> getByAirlineName(String airlineName);

    List<FlightDto> getByFlightNumber(String flightNumber);
}
