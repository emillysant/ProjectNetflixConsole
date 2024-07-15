package org.example.repository;

import jakarta.persistence.EntityExistsException;
import org.example.entity.WatchedSeries;
import org.hibernate.SessionFactory;

import java.util.List;

public class WatchedSeriesRepository {
    private final SessionFactory sessionFactory;

    public WatchedSeriesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean create(WatchedSeries watchedSeries) {
        var session = sessionFactory.openSession();

        var tx = session.beginTransaction();
        session.persist(watchedSeries);
        try {
            tx.commit();
            return true;
        } catch (EntityExistsException e) {
            return false;
        }
    }

    public List<WatchedSeries> findByProfileId(int profileId) {
        var session = sessionFactory.openSession();

        return session.createQuery("from WatchedSeries ws join ws.profile p join ws.seriesEpisode e where p.id = :profileId order by ws.createdAt desc", WatchedSeries.class)
                .setParameter("profileId", profileId)
                .list();
    }
}
