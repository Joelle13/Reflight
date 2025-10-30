package fr.joellehuyen.AirFrance_FlightReviews.dtos;

import fr.joellehuyen.AirFrance_FlightReviews.models.Flight;
import fr.joellehuyen.AirFrance_FlightReviews.models.Review;
import fr.joellehuyen.AirFrance_FlightReviews.models.ReviewStatus;
import fr.joellehuyen.AirFrance_FlightReviews.models.User;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


public record ReviewResponseDto (
    int rating,
    String comments,
    ReviewStatus status,
    String response,
    String id,
    LocalDate reviewDate,
    Flight flight,
    User user
) {
    public static ReviewResponseDto mapToDTO(Review review) {
        return new ReviewResponseDto(
                review.getRating(),
                review.getComments(),
                review.getStatus(),
                review.getResponse(),
                review.getId(),
                review.getReviewDate(),
                review.getFlight(),
                review.getUser()
        );
    }
}
