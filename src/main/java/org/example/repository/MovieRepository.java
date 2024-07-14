package org.example.repository;

import org.example.entity.Movie;
import org.hibernate.SessionFactory;

import java.util.List;

public class MovieRepository {

    public final SessionFactory sessionFactory;


    public MovieRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Movie> findAllMovies() {

        var session = sessionFactory.openSession();
        List<Movie> movies = session.createQuery("FROM Movie", Movie.class).list();
        return movies;
    }


}
