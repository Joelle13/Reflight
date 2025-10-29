import {Component, Input} from '@angular/core';
import {Review} from "../../data/review";

@Component({
  selector: 'app-review-list-item',
  templateUrl: './review-list-item.component.html',
  styleUrl: './review-list-item.component.css'
})
export class ReviewListItemComponent {
  @Input()
  review!: Review;

}
