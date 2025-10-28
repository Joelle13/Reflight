package fr.joellehuyen.AirFrance_FlightReviews.repositories;

import fr.joellehuyen.AirFrance_FlightReviews.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
