package fr.joellehuyen.reflights.services.impl;

import fr.joellehuyen.reflights.dtos.RequestFlightDto;
import fr.joellehuyen.reflights.exceptions.AirlineNotFoundException;
import fr.joellehuyen.reflights.exceptions.FlightNotFoundException;
import fr.joellehuyen.reflights.models.Airline;
import fr.joellehuyen.reflights.models.Flight;
import fr.joellehuyen.reflights.repositories.AirlineRepository;
import fr.joellehuyen.reflights.repositories.FlightRepository;
import fr.joellehuyen.reflights.services.FlightService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;
    private AirlineRepository airlineRepository;

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight createFlight(RequestFlightDto flightDto) {
        Airline airline = airlineRepository.findById(flightDto.getAirlineId())
                .orElseThrow(() -> new AirlineNotFoundException(flightDto.getAirlineId()));
        Flight flight = RequestFlightDto.mapToEntity(flightDto, airline);
        log.info("flight created {}", flight);
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> searchFlights(LocalDate date, String airline, String number) {
        String airlineToUpper = toUpperOrNull(airline);
        String numberToUpper = toUpperOrNull(number);
        return flightRepository.searchFlights(date, airlineToUpper, numberToUpper);
    }

    @Override
    public Flight getFlightByFlightNumber(String flightNumber) {
        return flightRepository.findById(flightNumber.toUpperCase())
                .orElseThrow(() -> {
                    log.error("Flight with number {} not found", flightNumber);
                    return new FlightNotFoundException(flightNumber);
                });
    }

    @Override
    public List<Flight> getSortedFlights(String sortBy, boolean desc) {
        Sort sort = buildSort(sortBy, desc);
        return flightRepository.findAll(sort);
    }

    @Override
    public void deleteFlightById(String id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Flight with number {} not found", id);
                    return new FlightNotFoundException(id);
                });
        flightRepository.delete(flight);
    }

    private Sort buildSort(String sortBy, boolean desc) {
        log.info("sort by {} desc ? {}", sortBy, desc);
        Sort.Direction direction = desc ? Sort.Direction.DESC : Sort.Direction.ASC;
        return switch (sortBy) {
            case "date" -> Sort.by(direction, "departureDate");
            case "airline" -> Sort.by(direction, "airline.name");
            case "flightNumber" -> Sort.by(direction, "id");
            default -> Sort.by(Sort.Direction.ASC, "departureDate");
        };
    }

    private String toUpperOrNull(String value) {
        return (value == null || value.isBlank()) ? null : value.trim().toUpperCase();
    }
}
