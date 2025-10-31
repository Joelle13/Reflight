export interface Airline {
  id:string;
  name:string;
  url:string
}
export type AirlineCreateInput = Omit<Airline, 'airlineId'>;
