package org.acme.domain.usecase;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.acme.domain.entity.Movie;
import org.acme.domain.exception.MovieNotFoundException;
import org.acme.domain.repository.MovieRepository;

@ApplicationScoped
public class DeleteMovieUseCase {

    @Inject
    MovieRepository movieRepository;
    
    @Transactional
    public void delete(Long id) throws MovieNotFoundException {
        Movie movie = this.movieRepository.getMovieById(id);
        if (movie == null) {
            throw new MovieNotFoundException("Movie" + id + "not found");
        }

        this.movieRepository.delete(id);
    }
}
