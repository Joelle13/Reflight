package fr.joellehuyen.reflights.dtos;

import fr.joellehuyen.reflights.models.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewDto {
    private String userId;
    private String flightId;
    private int rating;
    private String comments;

    public static ReviewDto mapToDTO(Review review) {
        return ReviewDto.builder()
        .userId(review.getUser().getId())
        .flightId(review.getFlight().getId())
        .rating(review.getRating())
        .comments(review.getComments())
        .build();
    }
}