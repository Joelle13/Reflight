import { Component } from '@angular/core';
import {Review} from "../../data/review";
import {ReviewService} from "../../services/reviewService";
import {Mode} from "../types/types";

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrl: './review-list.component.css'
})
export class ReviewListComponent {
  reviews:Review[] = [];
  mode: Mode = 'user';

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
}
