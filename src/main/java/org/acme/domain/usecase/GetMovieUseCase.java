package org.acme.domain.usecase;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.acme.domain.Movie;
import org.acme.domain.MovieRepository;
import org.acme.domain.exception.MovieNotFoundException;

@ApplicationScoped
public class GetMovieUseCase {

    @Inject
    MovieRepository movieRepository;

    public Movie getById(Long id) throws MovieNotFoundException {
        Movie movie = movieRepository.getMovieById(id);
        if(movie == null) {
            throw new MovieNotFoundException("Movie" + id + "not found");
        }
        return movie;
    }

    public Movie getByTitle(String title) throws MovieNotFoundException {
        Movie movie = movieRepository.getMovieByTitle(title);
        if(movie == null) {
            throw new MovieNotFoundException("Movie" + title + "not found");
        }
        return movie;
    }
}
