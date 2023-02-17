package org.acme.domain.repository;
import java.util.List;
import org.acme.domain.entity.Movie;

public interface MovieRepository {
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    Movie getMovieByTitle(String title);
    Movie create(Movie movie);
    Movie update(Long id, Movie updatedMovie);
    void delete(Long id);
}
