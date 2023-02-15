package org.acme.domain.exception;

public class MovieAlreadyCreatedException extends Exception{
    public MovieAlreadyCreatedException(String message) {
        super(message);
    }
}
