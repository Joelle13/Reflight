package fr.joellehuyen.reflights.repositories;

import fr.joellehuyen.reflights.models.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline,String> {
}
