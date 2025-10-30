import {Injectable} from "@angular/core";
import {environment} from "../environnement/environnement";
import {Flight, FlightCreateInput} from "../data/flight";
import BaseService from "./baseService";

@Injectable()
export class FlightService extends BaseService<Flight, FlightCreateInput>{
  private flightsUrl = `${environment.apiUrl}v1/flights`;

  getEndpointUrl(): string {
    return "v1/flights";
  }

  searchFlights(params: any) {
    return this.http.get<Flight[]>(`${this.flightsUrl}/search`, { params });
  }

  sortFlights(sortValue: string, desc: boolean) {
    return this.http.get<Flight[]>(`${this.flightsUrl}/sorted`, { params: { sortBy: sortValue, desc: desc } });

  }
}
