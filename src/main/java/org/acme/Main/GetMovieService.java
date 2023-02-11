package org.acme.Main;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import org.acme.Data.PanacheMovieRepository;
import org.acme.Entities.Movie;
import org.acme.Repositories.MovieRepository;
import org.acme.UseCases.GetMovie;
import org.acme.UseCases.GetMovieImpl;
import org.acme.UseCases.Exceptions.MovieNotFoundException;

@Singleton
public class GetMovieService {
    GetMovie getMovie;
    
    public GetMovieService() {
        MovieRepository movieRepository = new PanacheMovieRepository();
        this.getMovie = new GetMovieImpl(movieRepository);
    }

    public Response handle(Long id) {
        try {
            Movie movie = this.getMovie.getById(id);
            return Response.ok(movie).build();
        }
        catch (MovieNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
