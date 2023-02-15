package org.acme.infra;
import javax.enterprise.context.RequestScoped;
import org.acme.app.dto.MovieDTO;
import org.acme.domain.Movie;
import org.acme.domain.MovieRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@RequestScoped
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
