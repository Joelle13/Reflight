import { Airline} from "./airline";

export interface Flight {
  flightId:string;
  departureAirport:string;
  arrivalAirport:string;
  departureDate:Date;
  arrivalDate:Date;
  departureTime:string;
  arrivalTime:string;
  airline:Airline;
}

export type FlightCreateInput = Omit<Flight,'flightId' |'airline' | 'departureAirport' | 'arrivalAirport' | 'departureDate'
 | 'arrivalDate' | 'departureTime' | 'arrivalTime'>&{
  airlineId:string;
}
