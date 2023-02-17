package org.acme.infra;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import org.acme.domain.entity.Movie;
import org.acme.domain.repository.MovieRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@RequestScoped
public class PanacheMovieRepository implements MovieRepository, PanacheRepository<PanacheMovie> {


    @Override
    public List<Movie> getAllMovies() {
        return this.listAll().stream().map(movie -> movie.toMovie()).collect(Collectors.toList());
    }
    
    @Override 
    public Movie getMovieById(Long id) {
         return this.findByIdOptional(id).map(movie -> movie.toMovie()).orElse(null);
    }

    @Override 
    public Movie getMovieByTitle(String title) {
         return this.find("title", title).singleResultOptional().map(movie -> movie.toMovie()).orElse(null);
    }

    @Override
    public Movie create(Movie movie) {
        PanacheMovie panacheMovie = new PanacheMovie();
        panacheMovie.setTitle(movie.getTitle());
        panacheMovie.setDescription(movie.getDescription());
        panacheMovie.setDirector(movie.getDirector());
        panacheMovie.setCountry(movie.getCountry());
        this.persist(panacheMovie);
        return panacheMovie.toMovie();
    }

    @Override
    public Movie update(Long id, Movie updatedMovie) {
        PanacheMovie panacheMovie = this.findById(id);
        panacheMovie.setTitle(updatedMovie.getTitle());
        panacheMovie.setDescription(updatedMovie.getDescription());
        panacheMovie.setDirector(updatedMovie.getDirector());
        panacheMovie.setCountry(updatedMovie.getCountry());
        return panacheMovie.toMovie();
    }

    @Override
    public void delete(Long id) {
        PanacheMovie panacheMovie = this.findById(id);
        panacheMovie.delete();
    }
}
