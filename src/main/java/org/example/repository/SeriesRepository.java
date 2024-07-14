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

        List<Series> series = session.createQuery("FROM Series", Series.class).list();

        return series;

    }


}
