package fr.joellehuyen.AirFrance_FlightReviews.repositories;

import fr.joellehuyen.AirFrance_FlightReviews.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query("SELECT f FROM Flight f WHERE DATE(f.departureTime) = :date")
    List<Flight> getFlightsByDepartureDate(LocalDate date);

    @Query ("SELECT f FROM Flight f WHERE f.airline.name = :airlineName")
    List<Flight> findByAirline(String airlineName);
}
