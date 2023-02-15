package org.acme.infra;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.acme.domain.Movie;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "movie")
public class PanacheMovie extends PanacheEntityBase{
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, unique = true)
    private String title;

    @Column(length = 200)
    private String description;

    private String director;

    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Movie toMovie() {
        Movie movie = new Movie(); 
        movie.setId(this.id);
        movie.setTitle(this.title);
        movie.setDescription(this.description);
        movie.setDirector(this.director);
        movie.setCountry(this.country);
        return movie;
    }
    
}
