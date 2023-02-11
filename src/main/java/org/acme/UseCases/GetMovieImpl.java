package org.acme.UseCases;
import org.acme.Entities.Movie;
import org.acme.Repositories.MovieRepository;
import org.acme.UseCases.Exceptions.MovieNotFoundException;

public class GetMovieImpl implements GetMovie{
    private MovieRepository movieRepository;

    public GetMovieImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie getById(Long id) throws MovieNotFoundException {
        Movie movie = movieRepository.getMovieById(id);
        if(movie == null) {
            throw new MovieNotFoundException("Movie" + id + "not found");
        }
        return movie;
    }

    @Override
    public Movie getByTitle(String title) {
        return movieRepository.getMovieByTitle(title);
    }
}
