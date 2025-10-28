package fr.joellehuyen.AirFrance_FlightReviews.services.impl;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.ReviewDto;
import fr.joellehuyen.AirFrance_FlightReviews.exceptions.FlightNotFoundException;
import fr.joellehuyen.AirFrance_FlightReviews.exceptions.ReviewNotFoundException;
import fr.joellehuyen.AirFrance_FlightReviews.exceptions.UserNotFoundException;
import fr.joellehuyen.AirFrance_FlightReviews.models.Flight;
import fr.joellehuyen.AirFrance_FlightReviews.models.Review;
import fr.joellehuyen.AirFrance_FlightReviews.models.ReviewStatus;
import fr.joellehuyen.AirFrance_FlightReviews.models.User;
import fr.joellehuyen.AirFrance_FlightReviews.repositories.FlightRepository;
import fr.joellehuyen.AirFrance_FlightReviews.repositories.ReviewRepository;
import fr.joellehuyen.AirFrance_FlightReviews.repositories.UserRepository;
import fr.joellehuyen.AirFrance_FlightReviews.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private  final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review createReview(ReviewDto review) {
        User user = userRepository.findById(review.getUserId())
                .orElseThrow(() -> new UserNotFoundException(review.getUserId()));
        Flight flight = flightRepository.findById(review.getFlightId())
                .orElseThrow(() -> new FlightNotFoundException(review.getFlightId()));
        Review newReview = new Review(user, flight, review.getRating(), review.getComments());
        return reviewRepository.save(newReview);
    }

    @Override
    public Review answerReview(String id, String answer) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        review.setResponse(answer);
        review.setStatus(ReviewStatus.PROCESSED);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> searchReviews(LocalDate date, Integer rating, String airlineName, String status, String flightId, String keyword) {
        ReviewStatus reviewStatus = null;
        if (status != null) {
            reviewStatus = ReviewStatus.valueOf(status.toUpperCase());
        }
        return reviewRepository.searchReviews(
                date,
                rating,
                toUpperOrNull(airlineName),
                reviewStatus,
                toUpperOrNull(flightId),
                keyword
        );
    }

    @Override
    public Review getReviewById(String id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @Override
    public void deleteReviewById(String id) {
        getReviewById(id);
        reviewRepository.deleteById(id);
    }

    private String toUpperOrNull(String value) {
        return (value == null || value.isBlank()) ? null : value.trim().toUpperCase();
    }
}
