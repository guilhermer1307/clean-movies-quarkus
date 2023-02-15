package org.acme.app.service;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import org.acme.app.dto.MovieDTO;
import org.acme.domain.Movie;
import org.acme.domain.MovieRepository;
import org.acme.domain.exception.MovieAlreadyCreatedException;
import org.acme.domain.exception.MovieNotFoundException;
import org.acme.domain.usecase.CreateMovieUseCase;
import org.acme.domain.usecase.GetMovieUseCase;
import org.acme.infra.PanacheMovieRepository;

@ApplicationScoped
public class MovieService {

    private CreateMovieUseCase createMovieUseCase;
    
    private GetMovieUseCase getMovieUseCase;

    public MovieService() {
        MovieRepository movieRepository = new PanacheMovieRepository();
        this.createMovieUseCase = new CreateMovieUseCase(movieRepository);
        this.getMovieUseCase = new GetMovieUseCase(movieRepository);
    }
    
    @Transactional
    public Response createMovie(MovieDTO movieDTO) {
        try {
            Movie movie = this.createMovieUseCase.create(movieDTO);
            return Response.ok(movie).build();
        } catch (MovieAlreadyCreatedException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    public Response getMovie(Long id) {
        try {
            Movie movie = this.getMovieUseCase.getById(id);
            return Response.ok(movie).build();
        }
        catch (MovieNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
