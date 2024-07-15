package org.example.repository;

import org.example.entity.Series;
import org.hibernate.SessionFactory;

import java.util.List;

public class SeriesRepository {
    private final SessionFactory sessionFactory;

    public SeriesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Series> findAllSeries() {
        var session = sessionFactory.openSession();

        return session.createQuery("from Series", Series.class).list();
    }

    public List<Series> searchByTitle(String title) {
        var session = sessionFactory.openSession();

        return session.createQuery("from Series where title ilike :query", Series.class)
                .setParameter("query", "%" + title + "%")
                .list();
    }

    public List<Series> findByCategory(String categoryName) {
        var session = sessionFactory.openSession();

        return session.createQuery(
                        "from Series s join s.categories c where c.name = :categoryName", Series.class)
                .setParameter("categoryName", categoryName)
                .list();

    }

    public List<Series> findSeriesByYear(Integer year) {
        var session = sessionFactory.openSession();
        return session.createQuery("FROM Series s WHERE YEAR(s.releaseDate) = :year", Series.class)
                .setParameter("year", year)
                .list();
    }

}
