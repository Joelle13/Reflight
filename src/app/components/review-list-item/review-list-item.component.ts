import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Review} from "../../data/review";
import {Mode} from "../utils/types";
import {ReviewService} from "../../services/reviewService";

@Component({
  selector: 'app-review-list-item',
  templateUrl: './review-list-item.component.html',
  styleUrl: './review-list-item.component.css'
})
export class ReviewListItemComponent {
  @Input()
  review!: Review;
  @Input() mode: Mode = 'user';

  @Output() refresh = new EventEmitter<void>(); // pour refresh la liste parent

  showResponseModal = false;
  selectedReview: Review | null = null;
  adminResponseText: string = '';

  constructor(private reviewService: ReviewService) {}

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

  //Admin actions
  openResponseModal(review: Review) {
    this.selectedReview = review;
    this.adminResponseText = review.response || '';
    this.showResponseModal = true;
  }

  closeResponseModal() {
    this.showResponseModal = false;
    this.selectedReview = null;
    this.adminResponseText = '';
  }

  submitResponse() {
    if (!this.selectedReview) return;
    console.log("Submitting admin response:", this.adminResponseText)
    this.reviewService.answerReview(this.selectedReview.id, this.adminResponseText)
      .subscribe({
        next: () => {
          this.closeResponseModal();
          this.refresh.emit(); // 🟢 on dit au parent de recharger la liste
        },
        error: err => console.error("Erreur réponse admin", err)
      });
  }

  rejectReview(review: Review) {
    if (!confirm("Confirmer le rejet de cet avis ?")) return;

    this.reviewService.rejectReview(review.id)
      .subscribe({
        next: () => this.refresh.emit(),
        error: err => console.error("Erreur rejet review", err)
      });
  }

  confirmDelete(review: Review) {
    if (!confirm("Supprimer définitivement cet avis ?")) return;

    this.reviewService.delete(review.id)
      .subscribe({
        next: () => this.refresh.emit(),
        error: err => console.error("Erreur suppression", err)
      });
  }

  publishReview(review: Review) {
    if (!confirm("Confirmer la publication de cet avis ?")) return;

    this.reviewService.publishReview(review.id)
      .subscribe({
        next: () => this.refresh.emit(),
        error: err => console.error("Erreur publication review", err)
      });
  }
}
