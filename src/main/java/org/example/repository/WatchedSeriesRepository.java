package org.example.repository;

import org.example.entity.WatchedSeries;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class WatchedSeriesRepository {
    private final SessionFactory sessionFactory;

    public WatchedSeriesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean create(WatchedSeries watchedSeries) {
        var session = sessionFactory.openSession();

        var tx = session.beginTransaction();
        try {
            session.persist(watchedSeries);
            tx.commit();
            return true;
        } catch (ConstraintViolationException e) {
            return false;
        }
    }

    public List<WatchedSeries> findByProfileId(int profileId) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from WatchedSeries ws join ws.profile p join ws.seriesEpisode e where p.id = :profileId order by ws.createdAt desc", WatchedSeries.class)
                    .setParameter("profileId", profileId)
                    .list();
        }
    }
}
