package fr.joellehuyen.AirFrance_FlightReviews.dtos;

import fr.joellehuyen.AirFrance_FlightReviews.models.Review;
import fr.joellehuyen.AirFrance_FlightReviews.models.ReviewStatus;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


public record ReviewResponseDto (
    int rating,
    String comments,
    String flightId,
    String airlineName,
    String departureAirport,
    String arrivalAirport,
    LocalDate departureDate,
    LocalDate arrivalDate,
    LocalTime departureTime,
    LocalTime arrivalTime,
    ReviewStatus status,
    String response,
    String id,
    LocalDate reviewDate
) {
    public static ReviewResponseDto mapToDTO(Review review) {
        return new ReviewResponseDto(
                review.getRating(),
                review.getComments(),
                review.getFlight().getId(),
                review.getFlight().getAirline().getName(),
                review.getFlight().getDepartureAirport(),
                review.getFlight().getArrivalAirport(),
                review.getFlight().getDepartureDate(),
                review.getFlight().getArrivalDate(),
                review.getFlight().getDepartureTime(),
                review.getFlight().getArrivalTime(),
                review.getStatus(),
                review.getResponse(),
                review.getId(),
                review.getReviewDate()
        );
    }
}
