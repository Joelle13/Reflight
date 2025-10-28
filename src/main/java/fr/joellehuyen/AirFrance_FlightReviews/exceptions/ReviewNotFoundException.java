package fr.joellehuyen.AirFrance_FlightReviews.exceptions;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String id) {
        super("Review with id : " + id + " not found /!\\");
    }
}
