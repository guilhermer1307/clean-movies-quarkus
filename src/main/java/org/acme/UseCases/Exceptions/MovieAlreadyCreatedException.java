package org.acme.UseCases.Exceptions;

public class MovieAlreadyCreatedException extends Exception{
    public MovieAlreadyCreatedException(String message) {
        super(message);
    }
}
