export interface Airline {
  airlineId:string;
  name:string;
}
export type AirlineCreateInput = Omit<Airline, 'airlineId'>;
