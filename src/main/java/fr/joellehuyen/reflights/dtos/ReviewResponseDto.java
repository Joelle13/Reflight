package fr.joellehuyen.reflights.dtos;

import fr.joellehuyen.reflights.models.Flight;
import fr.joellehuyen.reflights.models.Review;
import fr.joellehuyen.reflights.models.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ReviewResponseDto {
    private int rating;
    private String comments;
    private String status;
    private String response;
    private String id;
    private LocalDate reviewDate;
    private Flight flight;
    private User user;

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
