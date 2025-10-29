import {Injectable} from "@angular/core";
import {environment} from "../environnement/environnement";
import {HttpClient} from "@angular/common/http";
import {Airline} from "../data/airline";

@Injectable()
export class AirlineService {
  private airlinesUrl = `${environment.apiUrl}v1/airlines`
  constructor(private http: HttpClient) {
  }

  getAirlines() {
    return this.http.get<Airline[]>(this.airlinesUrl);
  }

}
