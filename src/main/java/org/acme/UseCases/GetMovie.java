package org.acme.UseCases;
import org.acme.Entities.Movie;
import org.acme.UseCases.Exceptions.MovieNotFoundException;

public interface GetMovie {
    Movie getById(Long id) throws MovieNotFoundException;
    Movie getByTitle(String title) throws MovieNotFoundException;
}
