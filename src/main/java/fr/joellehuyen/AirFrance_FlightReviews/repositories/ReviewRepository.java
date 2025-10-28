package fr.joellehuyen.AirFrance_FlightReviews.repositories;

import fr.joellehuyen.AirFrance_FlightReviews.models.Review;
import fr.joellehuyen.AirFrance_FlightReviews.models.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    @Query("SELECT r FROM Review r WHERE DATE(r.reviewDate) = :date")
    List<Review> findByReviewDate(LocalDate date);

    @Query("SELECT r FROM Review r WHERE r.rating <= :rating")
    List<Review> findByRating(int rating);

    @Query("SELECT r FROM Review r WHERE r.flight.airline.name = :airlineName")
    List<Review> findByFlight_Airline_Name(String airlineName);

    List<Review> findByStatus(ReviewStatus reviewStatus);

    List<Review> findByFlight_Id(String flightId);

    @Query("SELECT r FROM Review r WHERE r.comments LIKE %:keyword%")
    List<Review> findByKeywordInComments(String keyword);
}
