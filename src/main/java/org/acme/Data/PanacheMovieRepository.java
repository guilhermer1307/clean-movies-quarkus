package org.acme.Data;
import javax.enterprise.context.ApplicationScoped;
import org.acme.Adapters.MovieDTO;
import org.acme.Entities.Movie;
import org.acme.Repositories.MovieRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PanacheMovieRepository implements MovieRepository, PanacheRepository<PanacheMovie> {
    
    @Override 
    public Movie getMovieById(Long id) {
         return this.findByIdOptional(id).map(movie -> movie.toMovie()).orElse(null);
    }

    @Override 
    public Movie getMovieByTitle(String title) {
         return this.find("title", title).singleResultOptional().map(movie -> movie.toMovie()).orElse(null);
    }

    @Override
    public Movie create(MovieDTO movieDTO) {
        PanacheMovie panacheMovie = new PanacheMovie();
        panacheMovie.setTitle(movieDTO.getTitle());
        panacheMovie.setDescription(movieDTO.getDescription());
        panacheMovie.setDirector(movieDTO.getDirector());
        panacheMovie.setCountry(movieDTO.getCountry());
        this.persist(panacheMovie);
        return panacheMovie.toMovie();
    }
}
