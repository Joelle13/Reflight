package fr.joellehuyen.AirFrance_FlightReviews.controllers;

import fr.joellehuyen.AirFrance_FlightReviews.dtos.ReviewDto;
import fr.joellehuyen.AirFrance_FlightReviews.dtos.ReviewResponseDto;
import fr.joellehuyen.AirFrance_FlightReviews.models.Review;
import fr.joellehuyen.AirFrance_FlightReviews.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @Operation (summary = "To answer a review")
    @PatchMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> answerReview(@PathVariable String id, @RequestBody String answer) {
        Review review = reviewService.answerReview(id, answer);
        ReviewResponseDto responseDto = ReviewResponseDto.mapToDTO(review);
        return ResponseEntity.ok(responseDto);
    }

    @Operation (summary = "To search reviews by various criteria")
    @GetMapping("/search")
    public ResponseEntity<List<ReviewResponseDto>> searchReviews(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) String airlineName,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String flightId,
            @RequestParam(required = false) String keyword) {

        List<Review> reviews = reviewService.searchReviews(date, rating, airlineName, status, flightId, keyword);
        List<ReviewResponseDto> reviewDtos = reviews.stream()
                .map(ReviewResponseDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewDtos);
    }

    @Operation (summary = "To get a review by its id")
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable String id) {
        Review review = reviewService.getReviewById(id);
        ReviewResponseDto responseDto = ReviewResponseDto.mapToDTO(review);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "To delete a review by its id")
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable String id) {
        reviewService.deleteReviewById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<ReviewResponseDto>> sortedReviews(
            @RequestParam String sortBy,
            @RequestParam boolean desc
    ) {
        List<Review> reviews = reviewService.getSortedReviews(sortBy, desc);
        List<ReviewResponseDto> reviewDtos = reviews.stream()
                .map(ReviewResponseDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewDtos);
    }

    @Operation (summary = "To count reviews by flight ID")
    @GetMapping("/count/flight/{flightId}")
    public ResponseEntity<Long> countReviewsByFlightId(@PathVariable String flightId) {
        long count = reviewService.countReviewsByFlightId(flightId);
        return ResponseEntity.ok(count);
    }

}
