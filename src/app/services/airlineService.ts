import {Injectable} from "@angular/core";
import {environment} from "../environnement/environnement";
import {Airline, AirlineCreateInput} from "../data/airline";
import BaseService from "./baseService";

@Injectable()
export class AirlineService extends BaseService<Airline, AirlineCreateInput>{
  private airlinesUrl = `${environment.apiUrl}v1/airlines`

  getEndpointUrl(): string {
    return "v1/airlines";
  }



}
