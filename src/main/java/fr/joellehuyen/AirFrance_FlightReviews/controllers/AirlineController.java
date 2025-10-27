package fr.joellehuyen.AirFrance_FlightReviews.controllers;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.AirlineDto;
import fr.joellehuyen.AirFrance_FlightReviews.models.Airline;
import fr.joellehuyen.AirFrance_FlightReviews.services.AirlineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/airlines")
@AllArgsConstructor
public class AirlineController {

    private AirlineService airlineService;

    @Operation (summary = "Get all airlines", description = "Retrieve a list of all available airlines")
    @GetMapping
    public List<ResponseEntity<AirlineDto>> getAllAirlines() {
        return airlineService.findAll().stream()
                .map(airline -> ResponseEntity.ok(AirlineDto.mapToDTO(airline)))
                .toList();
    }

    @Operation (summary = "Create a new airline", description = "Create a new airline with the provided details")
    @PostMapping
    public ResponseEntity<AirlineDto> createAirline(@RequestBody AirlineDto airlineDto) {
        Airline airline = airlineService.createAirline(airlineDto);
        return ResponseEntity.ok(AirlineDto.mapToDTO(airline));
    }
}
