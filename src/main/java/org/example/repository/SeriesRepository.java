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

        List<Series> series = session.createQuery("from Series", Series.class).list();

        return series;

    }

    public List<Series> searchByTitle(String title) {
        var session = sessionFactory.openSession();
        List<Series> series = session.createQuery("from Series where title ilike :query", Series.class)
                .setParameter("query", "%" + title + "%")
                .list();

        return series;
    }




}
