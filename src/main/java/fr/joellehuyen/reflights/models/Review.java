package fr.joellehuyen.reflights.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable=false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    private int rating;
    private String comments;
    private LocalDate reviewDate;
    private ReviewStatus status;
    private String response;

    public Review(User user, Flight flight, int rating, String comments) {
        this.user = user;
        this.flight = flight;
        this.rating = rating;
        this.comments = comments;
        this.reviewDate = LocalDate.now();
        this.status = ReviewStatus.PUBLISHED;
    }
}
