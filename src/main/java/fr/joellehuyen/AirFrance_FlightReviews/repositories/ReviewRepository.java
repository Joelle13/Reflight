package fr.joellehuyen.AirFrance_FlightReviews.repositories;

import fr.joellehuyen.AirFrance_FlightReviews.models.Review;
import fr.joellehuyen.AirFrance_FlightReviews.models.ReviewStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    @Query("""
SELECT r
FROM Review r
JOIN r.flight f
JOIN f.airline a
WHERE r.reviewDate = COALESCE(:date, r.reviewDate)
  AND r.rating     = COALESCE(:rating, r.rating)
  AND a.name = COALESCE(:airlineName, a.name)
  AND r.status      = COALESCE(:status, r.status)
  AND f.id          = COALESCE(:flightId, f.id)
  AND LOWER(r.comments) LIKE COALESCE(CONCAT('%', LOWER(:keyword), '%'), LOWER(r.comments))
""")
    List<Review> searchReviews(LocalDate date, Integer rating, String airlineName, ReviewStatus status, String flightId, String keyword);

    @Query ("""
        SELECT COUNT(r)
        FROM Review r
        JOIN r.flight f
        WHERE UPPER(f.id) = :flightId
    """)
    long countByFlight_FlightId(String flightId);

    Optional<Review> findByUser_idAndFlight_id(String userId, String flightId);

    @Query ("""
        SELECT r
        FROM Review r
        WHERE r.id IN :reviewIds
    """)
    List<Review> findAllById(List<String> reviewIds, Sort sort);
}
