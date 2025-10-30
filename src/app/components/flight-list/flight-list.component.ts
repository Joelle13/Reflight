import { Component } from '@angular/core';
import {Flight} from "../../data/flight";
import {FlightService} from "../../services/flightService";

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrl: './flight-list.component.css'
})
export class FlightListComponent {
  flights:Flight[] = [];

  constructor(private flightService: FlightService) {}
  ngOnInit(): void {
    this.flightService.getAll().subscribe(flights => {
      this.flights = flights;
    })
  }

  updateFlights(newFlights: Flight[]) {
    this.flights = newFlights;
  }
}
