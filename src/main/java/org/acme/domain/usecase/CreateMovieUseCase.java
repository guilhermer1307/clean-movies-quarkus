package org.acme.domain.usecase;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.acme.app.dto.MovieDTO;
import org.acme.domain.Movie;
import org.acme.domain.MovieRepository;
import org.acme.domain.exception.MovieAlreadyCreatedException;

@ApplicationScoped
public class CreateMovieUseCase {

    @Inject
    MovieRepository movieRepository;
    
    @Transactional
    public Movie create(MovieDTO movieDTO) throws MovieAlreadyCreatedException {
        Movie movie = this.movieRepository.getMovieByTitle(movieDTO.getTitle());
        if (movie != null) {
            throw new MovieAlreadyCreatedException("Movie with" + movie.getTitle() + "already created");
        }
        return this.movieRepository.create(movieDTO);
    }
}

