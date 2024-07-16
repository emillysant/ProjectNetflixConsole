package org.example.repository;

import org.example.entity.WatchedSeries;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

/**
 * This class provides methods for interacting with the WatchedSeries entity in the database.
 */
public class WatchedSeriesRepository {
    private final SessionFactory sessionFactory;

    /**
     * Constructor for WatchedSeriesRepository.
     *
     * @param sessionFactory The Hibernate SessionFactory for database operations.
     */
    public WatchedSeriesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Creates a new WatchedSeries record in the database.
     *
     * @param watchedSeries The WatchedSeries object to be created.
     * @return True if the record is successfully created, false if a constraint violation occurs.
     */
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

    /**
     * Retrieves a list of WatchedSeries records associated with a specific profile.
     *
     * @param profileId The ID of the profile for which to retrieve WatchedSeries records.
     * @return A list of WatchedSeries records, ordered by creation date in descending order.
     */
    public List<WatchedSeries> findByProfileId(int profileId) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from WatchedSeries ws join ws.profile p join ws.seriesEpisode e where p.id = :profileId order by ws.createdAt desc", WatchedSeries.class)
                    .setParameter("profileId", profileId)
                    .list();
        }
    }
}
