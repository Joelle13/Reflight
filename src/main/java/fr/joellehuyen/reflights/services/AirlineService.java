package fr.joellehuyen.reflights.services;


import fr.joellehuyen.reflights.dtos.AirlineDto;
import fr.joellehuyen.reflights.models.Airline;

import java.util.List;

public interface AirlineService {
    List<Airline> findAll();

    Airline createAirline(AirlineDto airlineDto);
}
