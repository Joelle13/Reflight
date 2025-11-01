package fr.joellehuyen.reflights.services.impl;

import fr.joellehuyen.reflights.dtos.ReviewDto;
import fr.joellehuyen.reflights.exceptions.ReviewAlreadyExistException;
import fr.joellehuyen.reflights.exceptions.ReviewNotFoundException;
import fr.joellehuyen.reflights.models.Flight;
import fr.joellehuyen.reflights.models.Review;
import fr.joellehuyen.reflights.models.ReviewStatus;
import fr.joellehuyen.reflights.models.User;
import fr.joellehuyen.reflights.repositories.ReviewRepository;
import fr.joellehuyen.reflights.services.FlightService;
import fr.joellehuyen.reflights.services.ReviewService;
import fr.joellehuyen.reflights.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private  final ReviewRepository reviewRepository;
    private final UserService userService;
    private final FlightService flightService;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review createReview(ReviewDto review) {
        User user = userService.findById(review.getUserId());
        Flight flight = flightService.findById(review.getFlightId().toUpperCase());
        if (reviewRepository.findByUser_idAndFlight_id(user.getId(), flight.getId()).isPresent()) {
            throw new ReviewAlreadyExistException(user, flight.getId());
        }
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

    @Override
    public List<Review> getSortedReviews(String sortBy, boolean desc, List<String> reviewIds) {
        Sort sort = buildSort(sortBy, desc);
        return reviewRepository.findAllById(reviewIds, sort);

    }

    @Override
    public long countReviewsByFlightId(String flightId) {
        return reviewRepository.countByFlight_FlightId(flightId.toUpperCase());
    }

    @Override
    public Review rejectReview(String id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        review.setStatus(ReviewStatus.REJECTED);
        return reviewRepository.save(review);
    }

    @Override
    public Review publishReview(String id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        review.setStatus(ReviewStatus.PUBLISHED);
        return reviewRepository.save(review);
    }

    private Sort buildSort(String sortBy, boolean desc) {
        Sort.Direction direction = desc ? Sort.Direction.DESC : Sort.Direction.ASC;
        return switch (sortBy) {
            case "date" -> Sort.by(direction, "reviewDate");
            case "rating" -> Sort.by(direction, "rating");
            case "airline" -> Sort.by(direction, "flight.airline.name");
            case "status" -> Sort.by(direction, "status");
            default -> Sort.by(Sort.Direction.ASC, "reviewDate");
        };
    }

    private String toUpperOrNull(String value) {
        return (value == null || value.isBlank()) ? null : value.trim().toUpperCase();
    }
}
