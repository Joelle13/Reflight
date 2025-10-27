package fr.joellehuyen.AirFrance_FlightReviews.dtos;

import fr.joellehuyen.AirFrance_FlightReviews.models.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public record ReviewResponseDto (
    int rating,
    String comments,
    String flightId,
    String airlineName,
    String departureAirport,
    String arrivalAirport,
    LocalDateTime departureTime,
    LocalDateTime arrivalTime
) {
    public static ReviewResponseDto mapToDTO(Review review) {
        return new ReviewResponseDto(
                review.getRating(),
                review.getComments(),
                review.getFlight().getId(),
                review.getFlight().getAirline().getName(),
                review.getFlight().getDepartureAirport(),
                review.getFlight().getArrivalAirport(),
                review.getFlight().getDepartureTime(),
                review.getFlight().getArrivalTime()
        );
    }
}
