package org.acme.UseCases;
import org.acme.Adapters.MovieDTO;
import org.acme.Entities.Movie;
import org.acme.UseCases.Exceptions.MovieAlreadyCreatedException;

public interface CreateMovie {
    Movie create(MovieDTO movie) throws MovieAlreadyCreatedException;
}
