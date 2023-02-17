package org.acme.app.rest;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.acme.app.dto.MovieDTO;
import org.acme.app.service.MovieService;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieEntrypoint {

    @Inject 
    MovieService movieService;

    @GET 
    public Response getAll() {
        return movieService.getAllMovies();
    }

    @GET
    @Path("{id}") 
    public Response getById(@PathParam("id") Long id) {
        return movieService.getMovie(id);
    }

    @POST
    @Transactional
    public Response create(MovieDTO movieDTO) {
        return movieService.createMovie(movieDTO);
    }

    @PUT 
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, MovieDTO movieDTO) {
        return movieService.updateMovie(id, movieDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        return movieService.deleteMovie(id);
    }
}
