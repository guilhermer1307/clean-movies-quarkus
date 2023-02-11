package org.acme.Repositories;
import org.acme.Adapters.MovieDTO;
import org.acme.Entities.Movie;

public interface MovieRepository {
    Movie getMovieById(Long id);
    Movie getMovieByTitle(String title);
    Movie create(MovieDTO movieDTO);
}
