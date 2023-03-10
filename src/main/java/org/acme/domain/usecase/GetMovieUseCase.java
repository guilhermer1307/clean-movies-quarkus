package org.acme.domain.usecase;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.acme.domain.entity.Movie;
import org.acme.domain.exception.MovieNotFoundException;
import org.acme.domain.repository.MovieRepository;

@ApplicationScoped
public class GetMovieUseCase {

    @Inject
    MovieRepository movieRepository;

    public List<Movie> getAll() {
        return movieRepository.getAllMovies();
    }

    public Movie getById(Long id) throws MovieNotFoundException {
        Movie movie = movieRepository.getMovieById(id);
        if(movie == null) {
            throw new MovieNotFoundException("Movie" + id + "not found");
        }
        return movie;
    }
}
