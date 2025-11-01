package fr.joellehuyen.reflights.services.impl;

import fr.joellehuyen.reflights.dtos.ReviewDto;
import fr.joellehuyen.reflights.exceptions.FlightNotFoundException;
import fr.joellehuyen.reflights.exceptions.ReviewAlreadyExistException;
import fr.joellehuyen.reflights.exceptions.ReviewNotFoundException;
import fr.joellehuyen.reflights.exceptions.UserNotFoundException;
import fr.joellehuyen.reflights.models.*;
import fr.joellehuyen.reflights.repositories.FlightRepository;
import fr.joellehuyen.reflights.repositories.ReviewRepository;
import fr.joellehuyen.reflights.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private User sampleUser() {
        User u = new User();
        u.setId("u1");
        u.setEmail("user@example.com");
        u.setFirstName("First");
        u.setLastName("Last");
        return u;
    }

    private Flight sampleFlight() {
        Airline a = new Airline(); a.setId("AF"); a.setName("AIR FRANCE");
        Flight f = new Flight(); f.setId("AF123"); f.setAirline(a); f.setDepartureDate(LocalDate.of(2025,1,1));
        return f;
    }

    @Test
    void shouldReturnAllReviews() {
        Review r1 = new Review();
        Review r2 = new Review();
        given(reviewRepository.findAll()).willReturn(List.of(r1, r2));

        List<Review> res = reviewService.getAllReviews();

        assertEquals(2, res.size());
        then(reviewRepository).should().findAll();
    }

    @Test
    void shouldThrow_whenCreateIfUserNotFound() {
        ReviewDto dto = ReviewDto.builder().userId("missing").flightId("AF123").rating(3).comments("ok").build();
        given(userRepository.findById("missing")).willReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> reviewService.createReview(dto));
    }

    @Test
    void shouldThrow_whenCreateIfFlightNotFound() {
        ReviewDto dto = ReviewDto.builder().userId("u1").flightId("missing").rating(3).comments("ok").build();
        given(userRepository.findById("u1")).willReturn(Optional.of(sampleUser()));
        given(flightRepository.findById("MISSING")).willReturn(Optional.empty());

        assertThrows(FlightNotFoundException.class, () -> reviewService.createReview(dto));
    }

    @Test
    void shouldThrow_whenCreateIfReviewAlreadyExists() {
        ReviewDto dto = ReviewDto.builder().userId("u1").flightId("af123").rating(3).comments("ok").build();
        given(userRepository.findById("u1")).willReturn(Optional.of(sampleUser()));
        given(flightRepository.findById("AF123")).willReturn(Optional.of(sampleFlight()));
        given(reviewRepository.findByUser_idAndFlight_id("u1", "AF123")).willReturn(Optional.of(new Review()));

        assertThrows(ReviewAlreadyExistException.class, () -> reviewService.createReview(dto));
    }

    @Test
    void shouldAnswerReview_andSetStatusProcessed() {
        Review r = new Review(); r.setId("r1"); r.setStatus(ReviewStatus.PUBLISHED);
        given(reviewRepository.findById("r1")).willReturn(Optional.of(r));
        given(reviewRepository.save(any(Review.class))).willAnswer(inv -> inv.getArgument(0));

        Review res = reviewService.answerReview("r1", "thanks");

        assertEquals("thanks", res.getResponse());
        assertEquals(ReviewStatus.PROCESSED, res.getStatus());
    }

    @Test
    void shouldSearchReviews_normalizeInputs() {
        LocalDate date = LocalDate.of(2025,5,5);
        given(reviewRepository.searchReviews(date, 5, "AIRLINE", null, "AF123", "key"))
                .willReturn(List.of());

        List<Review> res = reviewService.searchReviews(date, 5, " airline ", null, " af123 ", "key");

        then(reviewRepository).should().searchReviews(date, 5, "AIRLINE", null, "AF123", "key");
        assertNotNull(res);
    }

    @Test
    void shouldGetReviewById_whenExists() {
        Review r = new Review(); r.setId("r1");
        given(reviewRepository.findById("r1")).willReturn(Optional.of(r));

        Review res = reviewService.getReviewById("r1");

        assertEquals(r, res);
    }

    @Test
    void shouldThrow_whenGetReviewByIdNotFound() {
        given(reviewRepository.findById("missing")).willReturn(Optional.empty());
        assertThrows(ReviewNotFoundException.class, () -> reviewService.getReviewById("missing"));
    }

    @Test
    void shouldDeleteReviewById_whenExists() {
        Review r = new Review(); r.setId("r1");
        given(reviewRepository.findById("r1")).willReturn(Optional.of(r));

        reviewService.deleteReviewById("r1");

        then(reviewRepository).should().deleteById("r1");
    }

    @Test
    void shouldGetSortedReviews_mapSort() {
        // given
        Review r = new Review();
        r.setId("r1");
        List<String> reviewIds = List.of("r1");
        Sort sort = Sort.by(Sort.Direction.ASC, "reviewDate"); // selon ton mapping "date" → "createdAt"
        given(reviewRepository.findAllById(reviewIds, sort))
                .willReturn(List.of(r));
        // when
        List<Review> res = reviewService.getSortedReviews(SortBy.DATE, false, reviewIds);
        // then
        assertEquals(1, res.size());
    }

    @Test
    void shouldCountReviews_uppercaseFlightId() {
        given(reviewRepository.countByFlight_FlightId("AF123")).willReturn(5L);

        long count = reviewService.countReviewsByFlightId("af123");

        assertEquals(5L, count);
    }

    @Test
    void shouldRejectAndPublishReview_changeStatus() {
        Review r = new Review(); r.setId("r1"); r.setStatus(ReviewStatus.PUBLISHED);
        given(reviewRepository.findById("r1")).willReturn(Optional.of(r));
        given(reviewRepository.save(any(Review.class))).willAnswer(inv -> inv.getArgument(0));

        Review rej = reviewService.rejectReview("r1");
        assertEquals(ReviewStatus.REJECTED, rej.getStatus());

        Review pub = reviewService.publishReview("r1");
        assertEquals(ReviewStatus.PUBLISHED, pub.getStatus());
    }
}
