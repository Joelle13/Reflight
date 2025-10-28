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

    @Operation (summary = "Get flight by flight number", description = "Retrieve a flight with the specified flight number")
    @GetMapping("/{flightNumber}")
    public ResponseEntity<FlightDto> getByFlightNumber(@PathVariable String flightNumber) {
        Flight flight = flightService.getFlightByFlightNumber(flightNumber);
        return ResponseEntity.ok(FlightDto.mapToDTO(flight));
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

        List<Flight> results = flightService.searchFlights(date, airline, number);

        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<FlightDto> flightDtos = results.stream()
                .map(FlightDto::mapToDTO)
                .toList();
        return ResponseEntity.ok(flightDtos);
    }
}
