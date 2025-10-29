import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../environnement/environnement";
import {Flight} from "../data/flight";

@Injectable()
export class FlightService {
  private flightsUrl = `${environment.apiUrl}v1/flights`;
  constructor(private http: HttpClient) {
  }

  getFlights() {
    return this.http.get<Flight[]>(this.flightsUrl);
  }

  getFlightById(flightId: string) {
    return this.http.get<Flight>(`${this.flightsUrl}/${flightId}`);
  }
}
