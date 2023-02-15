package org.acme.domain;
import org.acme.app.dto.MovieDTO;

public interface MovieRepository {
    Movie getMovieById(Long id);
    Movie getMovieByTitle(String title);
    Movie create(MovieDTO movieDTO);
}
