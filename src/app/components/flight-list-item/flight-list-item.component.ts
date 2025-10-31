import {Component, Input} from '@angular/core';
import {Flight} from "../../data/flight";
import {ReviewService} from "../../services/reviewService";
import {FormBuilder,Validators} from "@angular/forms";
import {UserService} from "../../services/userService";
import {Router} from "@angular/router";
import {displayToast} from "../utils/functions";
import {User} from "../../data/user";

@Component({
  selector: 'app-flight-list-item',
  templateUrl: './flight-list-item.component.html',
  styleUrl: './flight-list-item.component.css'
})
export class FlightListItemComponent {
  @Input()
  flight!: Flight;
  reviewCount: number | null = null;
  reviewCreateInput: any;
  userCreateInput: any;
  showReviewModal: boolean = false

  // Rating
  selectedRating: number = 0;
  hoveredRating: number = 0;

  constructor(private router: Router, private formBuilder: FormBuilder,private reviewService: ReviewService, private userServie:UserService) {}

  ngOnInit(): void {
    if (this.flight && this.flight.id) {
      this.reviewService.getCountByFlightId(this.flight.id).subscribe({
        next: (count) => this.reviewCount = count,
        error: (err) => {
          console.error("Erreur lors de la récupération du nombre d'avis :", err);
          this.reviewCount = 0;
        }
      });
    }
  }

  addReview = this.formBuilder.nonNullable.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    rating: [0, [Validators.required, Validators.min(1), Validators.max(5)]],
    comments: ['', Validators.required]
  });

  get firstName() {
    return this.addReview.controls.firstName;
  }
  get lastName() {
    return this.addReview.controls.lastName;
  }
  get email() {
    return this.addReview.controls.email;
  }
  get rating() {
    return this.addReview.controls.rating!;
  }
  get comments() {
    return this.addReview.controls.comments;
  }


  closeReviewModal() {
    this.showReviewModal = false;
    this.resetForm();
  }

  openReviewModal() {
    this.showReviewModal = true;
    this.resetForm();
  }

  getRatingLabel(rating: number): string {
    const labels: Record<number, string> = {
      1: '⭐ Très décevant',
      2: '⭐⭐ Décevant',
      3: '⭐⭐⭐ Moyen',
      4: '⭐⭐⭐⭐ Bon',
      5: '⭐⭐⭐⭐⭐ Excellent'
    };
    return labels[rating] || '';
  }

  private resetForm() {
    this.addReview.reset();
    this.selectedRating = 0;
    this.hoveredRating = 0;
  }

  setRating(star: number) {
    this.selectedRating = star;
    this.addReview.patchValue({ rating: star });
  }

  submitReview() {
    const email = this.addReview.controls.email.value;
    if (this.addReview.valid) {
      this.userServie.findByEmail(email).subscribe({
        next: (user) => {
          this.createReview(user);
        },
        error: () => {
          this.userCreateInput = {
            firstName: this.addReview.controls.firstName.value ?? '',
            lastName: this.addReview.controls.lastName.value ?? '',
            email
          }
          this.userServie.create(this.userCreateInput).subscribe(user => {
            this.createReview(user);
          });
        }
      });
    }
  }


  private createReview(user: User) {
    this.reviewCreateInput = {
      flightId: this.flight.id,
      userId: user.id,
      rating: this.addReview.controls.rating.value ?? 0,
      comments: this.addReview.controls.comments.value ?? ''
    };
    this.reviewService.create(this.reviewCreateInput).subscribe({
      next: () => {
        this.closeReviewModal();
        this.router.navigate(['/']).then(() => window.location.reload())
        displayToast(true, "Avis soumis avec succès !");
      },
      error: () => {
        this.closeReviewModal();
        displayToast(false, "Erreur lors de la soumission de l'avis.");
      }
    });
  }
}
