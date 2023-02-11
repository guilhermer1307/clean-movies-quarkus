package org.acme.Presentation;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.acme.Adapters.MovieDTO;
import org.acme.Main.CreateMovieService;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreateMovieResource {
    @Inject 
    CreateMovieService createMovieService;

    @POST 
    @Transactional
    public Response create(MovieDTO movieDTO) {
        return this.createMovieService.handle(movieDTO);
    }
}
