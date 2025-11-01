package fr.joellehuyen.reflights.services;

import fr.joellehuyen.reflights.dtos.RequestFlightDto;
import fr.joellehuyen.reflights.models.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    List<Flight> findAll();
    Flight createFlight(RequestFlightDto flightDto);
    List<Flight> searchFlights(LocalDate date, String airline, String number);
    Flight getFlightByFlightNumber(String flightNumber);

    List<Flight> getSortedFlights(String sortBy, boolean desc);

    void deleteFlightById(String id);

    Flight findById(String upperCase);
}
