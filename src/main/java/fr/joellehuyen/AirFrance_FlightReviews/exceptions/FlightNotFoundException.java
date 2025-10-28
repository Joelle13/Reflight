package fr.joellehuyen.AirFrance_FlightReviews.exceptions;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String id) {
        super("Flight with id : " + id + " not found /!\\");
    }
}
