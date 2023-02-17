package org.acme.domain.usecase;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.acme.domain.entity.Movie;
import org.acme.domain.exception.MovieNotFoundException;
import org.acme.domain.exception.MovieTitleAlreadyExists;
import org.acme.domain.repository.MovieRepository;

@ApplicationScoped
public class UpdateMovieUseCase {

    @Inject
    MovieRepository movieRepository;    

    @Transactional
    public Movie update(Long id, Movie updatedMovie) throws MovieNotFoundException, IllegalArgumentException, MovieTitleAlreadyExists {
        Movie movie = this.movieRepository.getMovieById(id);
        
        if (movie == null) {
            throw new MovieNotFoundException("Movie" + id + "not found");
        }

        String title = updatedMovie.getTitle();
        String description = updatedMovie.getDescription();
        String director = updatedMovie.getDirector();
        String country = updatedMovie.getCountry();

        Movie movieTitleCheck = this.movieRepository.getMovieByTitle(title);

        if (movieTitleCheck != null) {
            throw new MovieTitleAlreadyExists("Movie with title" + title + "already exists");
        } 
        
        if ((title == null || title.isEmpty()) &&
            (description == null || description.isEmpty()) &&
            (director == null || director.isEmpty()) &&
            (country == null || country.isEmpty())) {
            throw new IllegalArgumentException("Movie fields cannot be all null or empty");
        }
        
        //KEEP OLD VALUES IF NEW VALUE == NULL OR EMPTY
        if (title == null || title.isEmpty()) {
            updatedMovie.setTitle(movie.getTitle());
        }
        if (description == null || description.isEmpty()) {
            updatedMovie.setDescription(movie.getDescription());
        }
        if (director == null || director.isEmpty()) {
            updatedMovie.setDirector(movie.getDirector());
        }
        if (country == null || country.isEmpty()) {
            updatedMovie.setCountry(movie.getCountry());
        }

        return this.movieRepository.update(id, updatedMovie);
    }
}
