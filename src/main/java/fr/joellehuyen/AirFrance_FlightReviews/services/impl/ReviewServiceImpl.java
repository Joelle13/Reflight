package fr.joellehuyen.AirFrance_FlightReviews.services.impl;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.ReviewDto;
import fr.joellehuyen.AirFrance_FlightReviews.exceptions.FlightNotFoundException;
import fr.joellehuyen.AirFrance_FlightReviews.exceptions.UserNotFoundException;
import fr.joellehuyen.AirFrance_FlightReviews.models.Flight;
import fr.joellehuyen.AirFrance_FlightReviews.models.Review;
import fr.joellehuyen.AirFrance_FlightReviews.models.User;
import fr.joellehuyen.AirFrance_FlightReviews.repositories.FlightRepository;
import fr.joellehuyen.AirFrance_FlightReviews.repositories.ReviewRepository;
import fr.joellehuyen.AirFrance_FlightReviews.repositories.UserRepository;
import fr.joellehuyen.AirFrance_FlightReviews.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
