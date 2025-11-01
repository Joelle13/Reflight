package fr.joellehuyen.reflights.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String id) {
        super("Review with id : " + id + " not found /!\\");
    }
}
