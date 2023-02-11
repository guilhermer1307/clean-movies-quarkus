package org.acme.UseCases.Exceptions;

public class MovieNotFoundException extends Exception{
    public MovieNotFoundException(String message) {
        super(message);
    }
}
