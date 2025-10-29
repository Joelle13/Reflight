import { Component } from '@angular/core';
import {Review} from "../../data/review";
import {ReviewService} from "../../services/reviewService";

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrl: './review-list.component.css'
})
export class ReviewListComponent {
  reviews:Review[] = [];

  constructor(private reviewService: ReviewService) {}
  ngOnInit(): void {
    this.reviewService.getAll().subscribe(reviews => {
      this.reviews = reviews;
    })
  }
}
