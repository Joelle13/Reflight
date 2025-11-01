package fr.joellehuyen.reflights.dtos;

import fr.joellehuyen.reflights.models.Flight;
import fr.joellehuyen.reflights.models.Review;
import fr.joellehuyen.reflights.models.User;

import java.time.LocalDate;


public record ReviewResponseDto (
    int rating,
    String comments,
    String status,
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
                review.getStatus().toString(),
                review.getResponse(),
                review.getId(),
                review.getReviewDate(),
                review.getFlight(),
                review.getUser()
        );
    }
}
