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

}
