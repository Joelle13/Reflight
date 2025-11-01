package fr.joellehuyen.reflights.dtos;

import fr.joellehuyen.reflights.models.SortBy;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewsSortingRequest {
    private SortBy sortBy;
    private boolean desc;
    private List<String> reviewIds;
}
