package fr.joellehuyen.AirFrance_FlightReviews.exceptions;

public class AirlineNotFoundException extends RuntimeException {
    public AirlineNotFoundException(String airline) {
        super("Airline not found: " + airline);
    }
}
