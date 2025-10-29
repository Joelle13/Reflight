import { Airline} from "./airline";

export interface Flight {
  id:string;
  departureAirport:string;
  arrivalAirport:string;
  departureDate:Date;
  arrivalDate:Date;
  departureTime:string;
  arrivalTime:string;
  airline:Airline;
}

export type FlightCreateInput = Omit<Flight,'flightId' |'airline'>&{
  airlineId:string;
}
