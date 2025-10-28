package fr.joellehuyen.AirFrance_FlightReviews.services.impl;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.FlightDto;
import fr.joellehuyen.AirFrance_FlightReviews.exceptions.AirlineNotFoundException;
import fr.joellehuyen.AirFrance_FlightReviews.exceptions.FlightNotFoundException;
import fr.joellehuyen.AirFrance_FlightReviews.models.Airline;
import fr.joellehuyen.AirFrance_FlightReviews.models.Flight;
import fr.joellehuyen.AirFrance_FlightReviews.repositories.AirlineRepository;
import fr.joellehuyen.AirFrance_FlightReviews.repositories.FlightRepository;
import fr.joellehuyen.AirFrance_FlightReviews.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;
    private AirlineRepository airlineRepository;

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight createFlight(FlightDto flightDto) {
        Airline airline = airlineRepository.findByName(flightDto.getAirlineName().toUpperCase());
        if(airline == null){
            throw new AirlineNotFoundException(flightDto.getAirlineName());
        }
        Flight flight = FlightDto.mapToEntity(flightDto, airline);
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> searchFlights(LocalDate date, String airline, String number) {
        String airlineToUpper = airline;
        String numberToUpper = number;
        if(airline != null){
            airlineToUpper = airline.toUpperCase();
        }
        if(number != null){
            numberToUpper = number.toUpperCase();
        }

        return flightRepository.searchFlights(date, airlineToUpper, numberToUpper);
    }

    @Override
    public Flight getFlightByFlightNumber(String flightNumber) {
        return flightRepository.findById(flightNumber.toUpperCase())
                .orElseThrow(() -> new FlightNotFoundException(flightNumber));
    }
}
