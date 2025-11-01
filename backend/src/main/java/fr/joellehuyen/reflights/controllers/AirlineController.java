package fr.joellehuyen.reflights.controllers;

import fr.joellehuyen.reflights.dtos.AirlineDto;
import fr.joellehuyen.reflights.models.Airline;
import fr.joellehuyen.reflights.services.AirlineService;
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
    public ResponseEntity<List<AirlineDto>> getAllAirlines() {
        List<Airline> airlines = airlineService.findAll();
        List<AirlineDto> airlineDtos = airlines.stream()
                .map(AirlineDto::mapToDTO)
                .toList();
        return ResponseEntity.ok(airlineDtos);
    }


    @Operation (summary = "Create a new airline", description = "Create a new airline with the provided details")
    @PostMapping
    public ResponseEntity<AirlineDto> createAirline(@RequestBody AirlineDto airlineDto) {
        Airline airline = airlineService.createAirline(airlineDto);
        return ResponseEntity.ok(AirlineDto.mapToDTO(airline));
    }
}
