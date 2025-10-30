import {Component, Input} from '@angular/core';
import {Review} from "../../data/review";
import {Mode} from "../types/types";

@Component({
  selector: 'app-review-list-item',
  templateUrl: './review-list-item.component.html',
  styleUrl: './review-list-item.component.css'
})
export class ReviewListItemComponent {
  @Input()
  review!: Review;

  @Input() mode: Mode = 'user';

  getStatusLabel(status: string) {
    switch (status) {
      case 'PROCESSED':
        return 'Traité';
      case 'PUBLISHED':
        return 'Publié';
      case 'REJECTED':
        return 'Rejeté';
      default:
        return 'Unknown';
    }
  }
}
