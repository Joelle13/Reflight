package fr.joellehuyen.AirFrance_FlightReviews.exceptions;

import fr.joellehuyen.AirFrance_FlightReviews.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReviewAlreadyExistException extends RuntimeException {
    public ReviewAlreadyExistException(User user, String flightNumber) {
        super("Review for flight " + flightNumber + " by user " + user.getEmail() + " already exists.");
    }
}
