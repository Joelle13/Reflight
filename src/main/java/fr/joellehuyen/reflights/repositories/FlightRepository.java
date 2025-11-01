package fr.joellehuyen.reflights.repositories;

import fr.joellehuyen.reflights.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,String> {
    @Query("""
        SELECT f FROM Flight f
        JOIN f.airline a
        WHERE f.departureDate = COALESCE(:date, f.departureDate)
        AND a.name          = COALESCE(:airline, a.name)
        AND (f.id)          LIKE COALESCE(CONCAT('%', :number, '%'), f.id)
    """)
    List<Flight> searchFlights(LocalDate date, String airline, String number);
}
