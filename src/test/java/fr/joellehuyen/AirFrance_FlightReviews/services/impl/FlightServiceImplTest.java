package fr.joellehuyen.AirFrance_FlightReviews.services.impl;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.RequestFlightDto;
import fr.joellehuyen.AirFrance_FlightReviews.exceptions.AirlineNotFoundException;
import fr.joellehuyen.AirFrance_FlightReviews.exceptions.FlightNotFoundException;
import fr.joellehuyen.AirFrance_FlightReviews.models.Airline;
import fr.joellehuyen.AirFrance_FlightReviews.models.Flight;
import fr.joellehuyen.AirFrance_FlightReviews.repositories.AirlineRepository;
import fr.joellehuyen.AirFrance_FlightReviews.repositories.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    private Flight sampleFlight(String id, LocalDate date, Airline airline) {
        Flight f = new Flight();
        f.setId(id);
        f.setDepartureDate(date);
        f.setAirline(airline);
        return f;
    }

    @Test
    void shouldReturnAllFlights() {
        Airline a = new Airline(); a.setId("AF"); a.setName("AIR FRANCE");
        Flight f1 = sampleFlight("AF123", LocalDate.of(2025,1,1), a);
        Flight f2 = sampleFlight("DL456", LocalDate.of(2025,2,2), a);

        given(flightRepository.findAll()).willReturn(Arrays.asList(f1, f2));

        List<Flight> result = flightService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        then(flightRepository).should().findAll();
    }

    @Test
    void shouldThrow_whenCreateFlightIfAirlineNotFound() {
        RequestFlightDto dto = RequestFlightDto.builder().airlineId("UNKNOWN").build();
        given(airlineRepository.findById("UNKNOWN")).willReturn(Optional.empty());

        assertThrows(AirlineNotFoundException.class, () -> flightService.createFlight(dto));
    }

    @Test
    void shouldSearchFlights_transformInputs() {
        LocalDate date = LocalDate.of(2025,3,3);
        given(flightRepository.searchFlights(date, "AIRLINE", "NUM")).willReturn(List.of());

        List<Flight> res = flightService.searchFlights(date, " airline ", " num ");

        then(flightRepository).should().searchFlights(date, "AIRLINE", "NUM");
        assertNotNull(res);
    }

    @Test
    void shouldGetFlightByNumber_whenExists() {
        Flight f = new Flight(); f.setId("AF123");
        given(flightRepository.findById("AF123")).willReturn(Optional.of(f));

        Flight res = flightService.getFlightByFlightNumber("af123");

        assertEquals(f, res);
    }

    @Test
    void shouldThrow_whenGetByNumberNotFound() {
        given(flightRepository.findById("UNKNOWN")).willReturn(Optional.empty());
        assertThrows(FlightNotFoundException.class, () -> flightService.getFlightByFlightNumber("unknown"));
    }

    @Test
    void shouldGetSortedFlights_mapSort() {
        Airline a = new Airline(); a.setId("AF"); a.setName("AIR FRANCE");
        Flight f = sampleFlight("AF123", LocalDate.of(2025,1,1), a);
        given(flightRepository.findAll(Sort.by(Sort.Direction.ASC, "departureDate"))).willReturn(List.of(f));

        List<Flight> res = flightService.getSortedFlights("date", false);

        assertEquals(1, res.size());
    }

    @Test
    void shouldDeleteFlight_whenExists() {
        Flight f = new Flight(); f.setId("AF123");
        given(flightRepository.findById("AF123")).willReturn(Optional.of(f));

        flightService.deleteFlightById("AF123");

        then(flightRepository).should().delete(f);
    }

    @Test
    void shouldThrow_whenDeleteNotFound() {
        given(flightRepository.findById("UNKNOWN")).willReturn(Optional.empty());
        assertThrows(FlightNotFoundException.class, () -> flightService.deleteFlightById("UNKNOWN"));
    }
}
