package fr.joellehuyen.reflights.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="flight")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    @Id
    private String id;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    @ManyToOne
    @JoinColumn(name="airline_id", nullable=false)
    private Airline airline;
}
