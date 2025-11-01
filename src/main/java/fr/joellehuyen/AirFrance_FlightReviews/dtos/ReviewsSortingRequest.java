package fr.joellehuyen.AirFrance_FlightReviews.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsSortingRequest {
    private String sortBy;
    private boolean desc;
    private List<String> reviewIds;
}
