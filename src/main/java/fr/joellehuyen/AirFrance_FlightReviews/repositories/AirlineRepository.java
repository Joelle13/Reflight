package fr.joellehuyen.AirFrance_FlightReviews.repositories;

import fr.joellehuyen.AirFrance_FlightReviews.models.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline,String> {
}
