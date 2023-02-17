package org.acme.app.service;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import org.acme.app.dto.MovieDTO;
import org.acme.domain.entity.Movie;
import org.acme.domain.exception.MovieAlreadyCreatedException;
import org.acme.domain.exception.MovieNotFoundException;
import org.acme.domain.exception.MovieTitleAlreadyExists;
import org.acme.domain.usecase.CreateMovieUseCase;
import org.acme.domain.usecase.DeleteMovieUseCase;
import org.acme.domain.usecase.GetMovieUseCase;
import org.acme.domain.usecase.UpdateMovieUseCase;

@ApplicationScoped
public class MovieService {

    @Inject
    CreateMovieUseCase createMovieUseCase;
    
    @Inject
    GetMovieUseCase getMovieUseCase;

    @Inject
    UpdateMovieUseCase updateMovieUseCase;

    @Inject 
    DeleteMovieUseCase deleteMovieUseCase;
    
    @Transactional
    public Response createMovie(MovieDTO movieDTO) {
        try {
            Movie movie = this.createMovieUseCase.create(movieFromDTO(movieDTO));
            return Response.ok(movie).build();
        } catch (MovieAlreadyCreatedException e) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Transactional 
    public Response updateMovie(Long id, MovieDTO movieDTO) {
        try {
            Movie movie = this.updateMovieUseCase.update(id, movieFromDTO(movieDTO));
            return Response.ok(movie).build();
        } catch (MovieNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (MovieTitleAlreadyExists e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Transactional 
    public Response deleteMovie(Long id) {
        try {
            this.deleteMovieUseCase.delete(id);
            return Response.noContent().build();
        } catch (MovieNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response getMovie(Long id) {
        try {
            Movie movie = this.getMovieUseCase.getById(id);
            return Response.ok(movie).build();
        } catch (MovieNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response getAllMovies() {
        try {
            List<Movie> movies = getMovieUseCase.getAll();
            return Response.ok(movies).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Movie movieFromDTO(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setDirector(movieDTO.getDirector());
        movie.setCountry(movieDTO.getCountry());
        return movie;
    }

}
