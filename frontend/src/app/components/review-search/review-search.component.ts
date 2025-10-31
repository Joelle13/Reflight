import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Airline} from "../../data/airline";
import {AirlineService} from "../../services/airlineService";
import {ReviewService} from "../../services/reviewService";
import {Mode, SortBy} from "../utils/types";
import {Review} from "../../data/review";

@Component({
  selector: 'app-review-search',
  templateUrl: './review-search.component.html',
  styleUrl: './review-search.component.css'
})
export class ReviewSearchComponent {
  airlines: Airline[] = [];
  searchFlightQuery: string = '';
  searchKeywordsQuery: string = '';
  filterStatus = '';
  filterAirlines = '';
  sortBy: SortBy = 'date-desc';

  reviews: Review[] = [];
  reviewCount = 0;

  @Output() reviewsFound = new EventEmitter<Review[]>();

  private lastCriteria: any = {};
  @Input() mode!: Mode;

  constructor(private reviewService: ReviewService, private airlineService: AirlineService) {}

  ngOnInit(): void {
    this.airlineService.getAll().subscribe(airlines => {
      this.airlines = airlines;
    })
    this.loadReviews();
    this.onSortChange();
  }

  onSearchReviews() {
    this.loadReviews();
  }

  onFilterChange() {
    this.loadReviews();
  }

  onSortChange() {
    let desc = false;
    const [sortField, sortDirection] = this.sortBy.split('-');
    if(sortDirection === 'desc') {
      desc = true;
    }
    this.reviewService.sortReviews(sortField, desc).subscribe(sorted => {
      this.reviews = sorted;
      this.reviewsFound.emit(this.reviews);
    });
  }

  private loadReviews() {const criteria: any = {};

    if (this.searchFlightQuery.trim()) criteria.flightId = this.searchFlightQuery.trim();
    if (this.searchKeywordsQuery.trim()) criteria.keyword = this.searchKeywordsQuery.trim();
    if (this.filterAirlines) criteria.airlineName = this.filterAirlines;
    if (this.filterStatus) criteria.status = this.filterStatus;

    this.lastCriteria = criteria;

    // Appel backend search
    this.reviewService.searchReviews(criteria).subscribe(reviews => {
      this.reviews = reviews;
      this.reviewCount = reviews.length;
      this.reviewsFound.emit(this.reviews);

    });
  }

  reload() {
    const criteria = this.lastCriteria || {};
    this.reviewService.searchReviews(criteria).subscribe(reviews => {
      this.reviewsFound.emit(reviews);
    });
  }
}
