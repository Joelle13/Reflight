package fr.joellehuyen.AirFrance_FlightReviews.services;


import fr.joellehuyen.AirFrance_FlightReviews.dtos.AirlineDto;
import fr.joellehuyen.AirFrance_FlightReviews.models.Airline;

import java.util.List;

public interface AirlineService {
    List<Airline> findAll();

    Airline createAirline(AirlineDto airlineDto);
}
