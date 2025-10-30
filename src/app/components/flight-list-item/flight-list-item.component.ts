import {Component, Input} from '@angular/core';
import {Flight} from "../../data/flight";
import {ReviewService} from "../../services/reviewService";

@Component({
  selector: 'app-flight-list-item',
  templateUrl: './flight-list-item.component.html',
  styleUrl: './flight-list-item.component.css'
})
export class FlightListItemComponent {
  @Input()
  flight!: Flight;
  reviewCount: number | null = null;

  constructor(private reviewService: ReviewService) {}

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
}
