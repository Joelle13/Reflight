package fr.joellehuyen.reflights.dtos;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class ReviewsSortingRequest {
    private String sortBy;
    private boolean desc;
    private List<String> reviewIds;
}
