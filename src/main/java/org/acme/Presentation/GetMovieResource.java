package org.acme.Presentation;
import javax.inject.Inject;
import org.acme.Main.GetMovieService;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetMovieResource {
    @Inject 
    GetMovieService getMovieService;

    @GET
    @Path("{id}") 
    public Response getById(@PathParam("id") Long id) {
        return getMovieService.handle(id);
    }
}
