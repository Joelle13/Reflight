package fr.joellehuyen.reflights.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String id) {
        super("Flight with id : " + id + " not found /!\\");
    }
}
