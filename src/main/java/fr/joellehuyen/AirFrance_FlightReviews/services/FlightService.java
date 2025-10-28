package fr.joellehuyen.AirFrance_FlightReviews.services;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.FlightDto;
import fr.joellehuyen.AirFrance_FlightReviews.models.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    List<Flight> findAll();
    Flight createFlight(FlightDto flightDto);
    List<Flight> searchFlights(LocalDate date, String airline, String number);
    Flight getFlightByFlightNumber(String flightNumber);

    List<Flight> getSortedFlights(String sortBy, boolean desc);
}
