package org.example.repository;

import org.example.entity.Series;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * This class provides methods for interacting with the Series entity in a database.
 * It uses Hibernate for database operations.
 */
public class SeriesRepository {
    private final SessionFactory sessionFactory;

    /**
     * Constructor for SeriesRepository.
     *
     * @param sessionFactory The Hibernate SessionFactory for database operations.
     */
    public SeriesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Retrieves all Series entities from the database.
     *
     * @return A list of all Series entities.
     */
    public List<Series> findAllSeries() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from Series", Series.class).list();
        }
    }

    /**
     * Searches for Series entities by title.
     *
     * @param title The title to search for.
     * @return A list of Series entities that match the given title.
     */
    public List<Series> searchByTitle(String title) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from Series where title ilike :query", Series.class)
                    .setParameter("query", "%" + title + "%")
                    .list();
        }
    }

    /**
     * Finds Series entities by category name.
     *
     * @param categoryName The name of the category to search for.
     * @return A list of Series entities that belong to the given category.
     */
    public List<Series> findByCategory(String categoryName) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery(
                            "from Series s join s.categories c where c.name = :categoryName", Series.class)
                    .setParameter("categoryName", categoryName)
                    .list();
        }
    }

    /**
     * Finds Series entities by release year.
     *
     * @param year The release year to search for.
     * @return A list of Series entities that were released in the given year.
     */
    public List<Series> findSeriesByYear(Integer year) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("FROM Series s WHERE YEAR(s.releaseDate) = :year", Series.class)
                    .setParameter("year", year)
                    .list();
        }
    }
}
