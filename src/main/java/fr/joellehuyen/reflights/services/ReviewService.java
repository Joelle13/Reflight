package fr.joellehuyen.reflights.services;

import fr.joellehuyen.reflights.dtos.ReviewDto;
import fr.joellehuyen.reflights.models.Review;
import fr.joellehuyen.reflights.models.SortBy;

import java.time.LocalDate;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();

    Review createReview(ReviewDto review);

    Review answerReview(String id, String answer);

    List<Review> searchReviews(LocalDate date, Integer rating, String airlineName, String status, String flightId, String keyword);

    Review getReviewById(String id);

    void deleteReviewById(String id);

    List<Review> getSortedReviews(SortBy sortBy, boolean desc, List<String> reviewIds);

    long countReviewsByFlightId(String flightId);

    Review rejectReview(String id);

    Review publishReview(String id);
}
