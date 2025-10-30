import {Component, EventEmitter, Output} from '@angular/core';
import {FlightService} from "../../services/flightService";
import {Airline} from "../../data/airline";
import {AirlineService} from "../../services/airlineService";
import {Flight} from "../../data/flight";

@Component({
  selector: 'app-flight-search',
  templateUrl: './flight-search.component.html',
  styleUrl: './flight-search.component.css'
})
export class FlightSearchComponent {
  flightNumber: string = '';
  airline: string = '';
  date: string = '';

  flights: any[] = [];
  airlines: Airline[] = [];

  @Output() flightsFound = new EventEmitter<Flight[]>();

  constructor(private flightService: FlightService, private airlineService: AirlineService) {}

  ngOnInit(): void {
    this.airlineService.getAll().subscribe(airlines => {
      this.airlines = airlines;
    })
    this.flightService.getAll().subscribe(flights => {
      this.flights = flights;
    })
  }


  onSearch(): void {
    const params: any = {};
    if (this.flightNumber.trim() !== '') {
      params.number = this.flightNumber;
    }
    if (this.airline.trim() !== '') {
      params.airline = this.airline;
    }
    if (this.date !== '') {
      params.date = this.date;
    }
    console.log('Recherche avec les paramètres :', params);
    this.flightService.searchFlights(params).subscribe({
      next: (data) => {
        this.flights = data;
        this.flightsFound.emit(this.flights);
        console.log('Résultats :', this.flights);
      },
      error: (err) => {
        console.error('Erreur lors de la recherche', err);
        this.flightsFound.emit([]);
        this.flights = [];
      }
    });
  }

  onSortChange(event: any) {
    const sortValue = event.target.value;
    this.flightService.sortFlights(sortValue, true).subscribe(sortedFlights => {
      this.flights = sortedFlights;
      this.flightsFound.emit(this.flights);
    }
    );

  }
}
