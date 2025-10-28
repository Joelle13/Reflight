package fr.joellehuyen.AirFrance_FlightReviews.services;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.ReviewDto;
import fr.joellehuyen.AirFrance_FlightReviews.models.Review;

import java.time.LocalDate;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();

    Review createReview(ReviewDto review);

    Review answerReview(String id, String answer);

    List<Review> getReviewsByDate(LocalDate date);

    List<Review> getReviewsByRating(int rating);

    List<Review> getReviewsByAirline(String airlineName);

    List<Review> getReviewsByStatus(String status);

    List<Review> getReviewsByFlightId(String flightId);

    List<Review> getReviewsByKeyword(String keyword);
}
