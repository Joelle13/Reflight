package fr.joellehuyen.AirFrance_FlightReviews.services.impl;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.FlightDto;
import fr.joellehuyen.AirFrance_FlightReviews.exceptions.AirlineNotFoundException;
import fr.joellehuyen.AirFrance_FlightReviews.models.Airline;
import fr.joellehuyen.AirFrance_FlightReviews.models.Flight;
import fr.joellehuyen.AirFrance_FlightReviews.repositories.AirlineRepository;
import fr.joellehuyen.AirFrance_FlightReviews.repositories.FlightRepository;
import fr.joellehuyen.AirFrance_FlightReviews.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Airline airline = airlineRepository.findByName(flightDto.getAirlineName());
        if(airline == null){
            throw new AirlineNotFoundException(flightDto.getAirlineName());
        }
        Flight flight = FlightDto.mapToEntity(flightDto, airline);
        return flightRepository.save(flight);
    }

    @Override
    public List<FlightDto> getByDate(LocalDate date) {
        List<Flight> flights = flightRepository.getFlightsByDepartureDate(date);
        return flights.stream().map(FlightDto::mapToDTO).toList();
    }

    @Override
    public List<FlightDto> searchFlights(LocalDate date, String airline, String number) {
        List<Flight> flights = flightRepository.findAll().stream()
                .filter(flight -> (date == null || flight.getDepartureTime().toLocalDate().equals(date)) &&
                        (airline == null || flight.getAirline().getName().equalsIgnoreCase(airline)) &&
                        (number == null || flight.getId().equalsIgnoreCase(number)))
                .toList();
        return flights.stream().map(FlightDto::mapToDTO).toList();
    }

    @Override
    public List<FlightDto> getByAirlineName(String airlineName) {
        List<Flight> flights = flightRepository.findByAirline(airlineName);
        return flights.stream().map(FlightDto::mapToDTO).toList();
    }

    @Override
    public List<FlightDto> getByFlightNumber(String flightNumber) {
        List<Flight> flights = flightRepository.findAll().stream()
                .filter(flight -> flight.getId().equalsIgnoreCase(flightNumber))
                .toList();
        return flights.stream().map(FlightDto::mapToDTO).toList();
    }
}
