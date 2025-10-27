package fr.joellehuyen.AirFrance_FlightReviews.services;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.ReviewDto;
import fr.joellehuyen.AirFrance_FlightReviews.models.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();

    Review createReview(ReviewDto review);
}
