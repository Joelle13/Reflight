import {Component, ViewChild} from '@angular/core';
import {Flight} from "../../data/flight";
import {FlightService} from "../../services/flightService";
import {FlightSearchComponent} from "../flight-search/flight-search.component";

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrl: './flight-list.component.css'
})
export class FlightListComponent {
  flights:Flight[] = [];
  loading: boolean = true;

  @ViewChild(FlightSearchComponent)
  flightSearch!: FlightSearchComponent;

  constructor(private flightService: FlightService) {}
  ngOnInit(): void {
    this.flightService.getAll().subscribe(flights => {
      this.flights = flights;
      this.loading = false;
    })
  }

  updateFlights(newFlights: Flight[]) {
    this.flights = newFlights;
  }

  resetSearch() {
    this.flightSearch.resetSearch();
  }
}
