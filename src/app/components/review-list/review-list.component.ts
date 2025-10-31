import {Component, ViewChild} from '@angular/core';
import {Review} from "../../data/review";
import {ReviewService} from "../../services/reviewService";
import {Mode} from "../utils/types";
import {ReviewSearchComponent} from "../review-search/review-search.component";

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrl: './review-list.component.css'
})
export class ReviewListComponent {
  reviews:Review[] = [];
  mode: Mode = 'user';

  @ViewChild(ReviewSearchComponent)
  reviewSearch!: ReviewSearchComponent;

  constructor(private reviewService: ReviewService) {}
  ngOnInit(): void {
    this.reviewService.getAll().subscribe(reviews => {
      this.reviews = reviews;
    })
  }

  onToggleMode(checked: boolean) {
    this.mode = checked ? 'admin' : 'user';
    console.log(this.mode);
  }

  updateReviews(newReviews: Review[]) {
    this.reviews = newReviews;
  }

  loadReviews() {
    if (this.reviewSearch) {
      this.reviewSearch.reload();
    } else {
      this.reviewService.getAll().subscribe(r => this.reviews = r);
    }
  }
}
