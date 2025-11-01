package fr.joellehuyen.AirFrance_FlightReviews.services;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.FlightDto;
import fr.joellehuyen.AirFrance_FlightReviews.dtos.RequestFlightDto;
import fr.joellehuyen.AirFrance_FlightReviews.models.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<Flight> findAll();
    Flight createFlight(RequestFlightDto flightDto);
    List<Flight> searchFlights(LocalDate date, String airline, String number);
    Flight getFlightByFlightNumber(String flightNumber);

    List<Flight> getSortedFlights(String sortBy, boolean desc);

    void deleteFlightById(String id);

    Flight findById(String upperCase);
}
