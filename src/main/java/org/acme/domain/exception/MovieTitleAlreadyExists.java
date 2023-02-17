package org.acme.domain.exception;

public class MovieTitleAlreadyExists extends Exception {
    public MovieTitleAlreadyExists(String message) {
        super(message);
    }
}
