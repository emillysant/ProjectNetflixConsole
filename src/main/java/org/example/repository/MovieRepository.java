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

        return session.createQuery("from Movie", Movie.class).list();
    }

    public List<Movie> searchByTitle(String title) {
        var session = sessionFactory.openSession();

        return session.createQuery("from Movie where title ilike :query", Movie.class)
                .setParameter("query", "%" + title + "%")
                .list();
    }

    public List<Movie> findByCategory(String categoryName) {
        var session = sessionFactory.openSession();

        return session.createQuery(
                        "from Movie m join m.categories c where c.name = :categoryName",
                        Movie.class)
                .setParameter("categoryName", categoryName)
                .list();
    }
}
