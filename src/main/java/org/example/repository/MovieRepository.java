package org.example.repository;

import org.example.entity.Movie;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * A repository for managing movie data.
 */
public class MovieRepository {
    /**
     * The Hibernate Session Factory for creating sessions.
     */
    public final SessionFactory sessionFactory;

    /**
     * Constructs a new MovieRepository with the given SessionFactory.
     *
     * @param sessionFactory The Hibernate Session Factory for creating sessions.
     */
    public MovieRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Retrieves all movies from the database.
     *
     * @return A list of all movies.
     */
    public List<Movie> findAllMovies() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from Movie", Movie.class).list();
        }
    }

    /**
     * Searches for movies by title.
     *
     * @param title The title to search for.
     * @return A list of movies that match the given title.
     */
    public List<Movie> searchByTitle(String title) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from Movie where title ilike :query", Movie.class)
                    .setParameter("query", "%" + title + "%")
                    .list();
        }
    }

    /**
     * Finds movies by category.
     *
     * @param categoryName The name of the category to search for.
     * @return A list of movies that belong to the given category.
     */
    public List<Movie> findByCategory(String categoryName) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery(
                            "from Movie m join m.categories c where c.name = :categoryName",
                            Movie.class)
                    .setParameter("categoryName", categoryName)
                    .list();
        }
    }

    /**
     * Finds movies by release year.
     *
     * @param year The release year to search for.
     * @return A list of movies that were released in the given year.
     */
    public List<Movie> findMoviesByYear(Integer year) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("FROM Movie m WHERE YEAR(m.releaseDate) = :year", Movie.class)
                    .setParameter("year", year)
                    .list();
        }
    }
}
