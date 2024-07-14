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
        List<Movie> movies = session.createQuery("from Movie", Movie.class).list();
        return movies;
    }

    public List<Movie> searchByTitle(String title) {
        var session = sessionFactory.openSession();
        List<Movie> movie = session.createQuery("from Movie where title ilike :query", Movie.class)
                .setParameter("query", "%" + title + "%")
                .list();
        return movie;
    }
}
