package org.acme.UseCases;
import javax.transaction.Transactional;
import org.acme.Adapters.MovieDTO;
import org.acme.Entities.Movie;
import org.acme.Repositories.MovieRepository;
import org.acme.UseCases.Exceptions.MovieAlreadyCreatedException;

public class CreateMovieImpl implements CreateMovie {
    private MovieRepository movieRepository;

    public CreateMovieImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override 
    @Transactional
    public Movie create(MovieDTO movieDTO) throws MovieAlreadyCreatedException{
        Movie movie = this.movieRepository.getMovieByTitle(movieDTO.getTitle());
        if (movie != null) {
            throw new MovieAlreadyCreatedException("Movie with" + movie.getTitle() + "already created");
        }
        return this.movieRepository.create(movieDTO);
    }
}

