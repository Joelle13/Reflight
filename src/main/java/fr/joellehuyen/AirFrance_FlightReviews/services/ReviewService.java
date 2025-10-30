package fr.joellehuyen.AirFrance_FlightReviews.services;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.ReviewDto;
import fr.joellehuyen.AirFrance_FlightReviews.models.Review;

import java.time.LocalDate;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();

    Review createReview(ReviewDto review);

    Review answerReview(String id, String answer);

    List<Review> searchReviews(LocalDate date, Integer rating, String airlineName, String status, String flightId, String keyword);

    Review getReviewById(String id);

    void deleteReviewById(String id);

    List<Review> getSortedReviews(String sortBy, boolean desc);

    long countReviewsByFlightId(String flightId);
}
