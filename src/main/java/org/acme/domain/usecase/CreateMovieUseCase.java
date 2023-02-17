package org.acme.domain.usecase;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.acme.domain.entity.Movie;
import org.acme.domain.exception.MovieAlreadyCreatedException;
import org.acme.domain.repository.MovieRepository;

@ApplicationScoped
public class CreateMovieUseCase {

    @Inject
    MovieRepository movieRepository;
    
    @Transactional
    public Movie create(Movie movie) throws MovieAlreadyCreatedException, IllegalArgumentException {
        Movie persistentMovie = this.movieRepository.getMovieByTitle(movie.getTitle());
        if (persistentMovie != null) {
            throw new MovieAlreadyCreatedException("Movie with" + movie.getTitle() + "already created");
        }

        String title = movie.getTitle();
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Movie title cannot be null or empty");
        }

        String description = movie.getDescription();
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Movie description cannot be null or empty");
        }

        String director = movie.getDirector();
        if (director == null || director.isEmpty()) {
            throw new IllegalArgumentException("Movie director cannot be null or empty");
        }

        String country = movie.getCountry();
        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException("Movie country cannot be null or empty");
        }

        return this.movieRepository.create(movie);
    }
}

