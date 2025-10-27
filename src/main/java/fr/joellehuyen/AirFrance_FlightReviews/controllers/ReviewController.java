package fr.joellehuyen.AirFrance_FlightReviews.controllers;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.ReviewDto;
import fr.joellehuyen.AirFrance_FlightReviews.dtos.ReviewResponseDto;
import fr.joellehuyen.AirFrance_FlightReviews.models.Review;
import fr.joellehuyen.AirFrance_FlightReviews.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/reviews")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "To get all reviews")
    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        List<ReviewResponseDto> reviewDtos = reviews.stream()
                .map(ReviewResponseDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewDtos);
    }

    @Operation (summary = "To create a new review")
    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto review) {
        Review createdReview = reviewService.createReview(review);
        ReviewDto responseDto = ReviewDto.mapToDTO(createdReview);
        return ResponseEntity.ok(responseDto);
    }
}
