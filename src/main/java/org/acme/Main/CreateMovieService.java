package org.acme.Main;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.acme.Adapters.MovieDTO;
import org.acme.Data.PanacheMovieRepository;
import org.acme.Entities.Movie;
import org.acme.Repositories.MovieRepository;
import org.acme.UseCases.CreateMovie;
import org.acme.UseCases.CreateMovieImpl;
import org.acme.UseCases.Exceptions.MovieAlreadyCreatedException;

@Singleton
public class CreateMovieService {
    CreateMovie createMovie;
    
    public CreateMovieService() {
        MovieRepository movieRepository = new PanacheMovieRepository();
        this.createMovie = new CreateMovieImpl(movieRepository);
    }

    @Transactional 
    public Response handle(MovieDTO movieDTO) {
        try {
            Movie movie = this.createMovie.create(movieDTO);
            return Response.ok(movie).build();
        }
        catch (MovieAlreadyCreatedException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
