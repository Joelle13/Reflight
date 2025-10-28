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

    @Operation (summary = "To get reviews by date")
    @GetMapping("/date/{date}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Review> reviews = reviewService.getReviewsByDate(date);
        List<ReviewResponseDto> reviewDtos = reviews.stream()
                .map(ReviewResponseDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewDtos);
    }

    @Operation (summary = "To get reviews by rating")
    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByRating(@PathVariable int rating) {
        List<Review> reviews = reviewService.getReviewsByRating(rating);
        List<ReviewResponseDto> reviewDtos = reviews.stream()
                .map(ReviewResponseDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewDtos);
    }

    @Operation (summary = "To get reviews by airline name")
    @GetMapping("/airline/{airlineName}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByAirline(@PathVariable String airlineName) {
        List<Review> reviews = reviewService.getReviewsByAirline(airlineName);
        List<ReviewResponseDto> reviewDtos = reviews.stream()
                .map(ReviewResponseDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewDtos);
    }

    @Operation (summary = "To get reviews by status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByStatus(@PathVariable String status) {
        List<Review> reviews = reviewService.getReviewsByStatus(status);
        List<ReviewResponseDto> reviewDtos = reviews.stream()
                .map(ReviewResponseDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewDtos);
    }

    @Operation (summary = "To get reviews by flight ID")
    @GetMapping("flight/{flightId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByFlightId(@PathVariable String flightId) {
        List<Review> reviews = reviewService.getReviewsByFlightId(flightId);
        List<ReviewResponseDto> reviewDtos = reviews.stream()
                .map(ReviewResponseDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewDtos);
    }

    @Operation (summary = "To get reviews by keyword in comments")
    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByKeyword(@PathVariable String keyword) {
        List<Review> reviews = reviewService.getReviewsByKeyword(keyword);
        List<ReviewResponseDto> reviewDtos = reviews.stream()
                .map(ReviewResponseDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewDtos);
    }
}
