package org.acme.domain.usecase;
import org.acme.domain.Movie;
import org.acme.domain.MovieRepository;
import org.acme.domain.exception.MovieNotFoundException;

public class GetMovieUseCase {

    private MovieRepository movieRepository;

    public GetMovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

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
