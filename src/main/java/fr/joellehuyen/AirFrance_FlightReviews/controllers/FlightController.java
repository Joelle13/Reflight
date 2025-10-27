package fr.joellehuyen.AirFrance_FlightReviews.controllers;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.FlightDto;
import fr.joellehuyen.AirFrance_FlightReviews.models.Flight;
import fr.joellehuyen.AirFrance_FlightReviews.services.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/flights")
@AllArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @Operation(summary = "Get all flights", description = "Retrieve a list of all available flights")
    @GetMapping
    public ResponseEntity<List<FlightDto>> getFlights() {
        List<Flight> flights = flightService.findAll();
        return ResponseEntity.ok(flights.stream().map(FlightDto::mapToDTO).toList());
    }

    @Operation (summary = "Get flights by flight number", description = "Retrieve a list of flights with the specified flight number")
    @GetMapping("/number/{flightNumber}")
    public ResponseEntity<List<FlightDto>> getByFlightNumber(@PathVariable String flightNumber) {
        List<FlightDto> flights = flightService.getByFlightNumber(flightNumber);
        if (flights.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(flights);
    }

    @Operation(summary = "Get flights by departure time", description = "Retrieve a list of flights departing at the specified time")
    @GetMapping("/date/{date}")
    public ResponseEntity<List<FlightDto>> getFlightsByDepartureTime(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<FlightDto> flights = flightService.getByDate(date);
        if (flights.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(flights);
    }

    @Operation(summary = "Get flights by airline", description = "Retrieve a list of flights operated by the specified airline")
    @GetMapping("/airline/{airlineName}")
    public ResponseEntity<List<FlightDto>> getByAirline(@PathVariable String airlineName) {
        List<FlightDto> flights = flightService.getByAirlineName(airlineName);
        if (flights.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(flights);
    }

    @Operation (summary = "Create a new flight", description = "Create a new flight with the provided details")
    @PostMapping
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto) {
        Flight flight = flightService.createFlight(flightDto);
        return ResponseEntity.ok(FlightDto.mapToDTO(flight));
    }

    @Operation (summary = "Search flights", description = "Search for flights based on date, airline, and flight number")
    @GetMapping("/search")
    public ResponseEntity<List<FlightDto>> searchFlights(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) String airline,
            @RequestParam(required = false) String number) {

        List<FlightDto> results = flightService.searchFlights(date, airline, number);

        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(results);
    }
}
