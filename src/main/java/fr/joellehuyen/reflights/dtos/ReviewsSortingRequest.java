package fr.joellehuyen.reflights.dtos;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewsSortingRequest {
    private String sortBy;
    private boolean desc;
    private List<String> reviewIds;
}
